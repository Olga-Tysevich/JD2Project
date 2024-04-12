package it.academy.services.repair.impl;


import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.device.req.BrandDTO;
import it.academy.dto.device.resp.DeviceDTOResp;
import it.academy.dto.device.req.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.Model;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.entities.repair.components.RepairType;
import it.academy.services.repair.RepairService;
import it.academy.utils.Builder;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.converters.repair.RepairConverter;
import it.academy.utils.converters.repair.RepairTypeConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.LIST_SIZE;
import static it.academy.utils.Constants.SERIAL_NUMBER;

public class RepairServiceImpl implements RepairService {
    private TransactionManger transactionManger = new TransactionManger();
    private RepairDAO repairDAO = new RepairDAOImpl();
    private RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl();
    private BrandDAO brandDAO = new BrandDAOImpl();
    private DeviceDAO deviceDAO = new DeviceDAOImpl();
    private ModelDAO modelDAO = new ModelDAOImpl();

    @Override
    public void addRepair(RepairDTO repairDTO) {
        Repair repair = RepairConverter.convertDTOToEntity(repairDTO);
        transactionManger.execute(() -> repairDAO.create(repair));
    }

    @Override
    public void updateRepair(RepairDTO repairDTO) {
        Repair repair = RepairConverter.convertDTOToEntity(repairDTO);
        transactionManger.execute(() -> repairDAO.update(repair));
    }


    @Override
    public List<BrandDTO> findBrands() {
        Supplier<List<BrandDTO>> find = () -> {
            List<Brand> result = brandDAO.findAll();
            return BrandConverter.convertListToDTO(result);
        };

        return transactionManger.execute(find);
    }

    @Override
    public DeviceDTOResp addDevice(DeviceDTOResp deviceDTOResp) {
        Device device = DeviceConverter.convertDTOToEntity(deviceDTOResp);
        device.setSerialNumber(device.getSerialNumber().toUpperCase());
        Device result = deviceDAO.findByUniqueParameter(SERIAL_NUMBER, device.getSerialNumber());

        if (result == null) {
            result = transactionManger.execute(() -> deviceDAO.create(device));
        }

        transactionManger.closeManager();
        return DeviceConverter.convertToDTO(result);
    }

    @Override
    public DeviceDTOResp updateDevice(DeviceDTOResp deviceDTOResp) {
        Device device = DeviceConverter.convertDTOToEntity(deviceDTOResp);
        device.setSerialNumber(device.getSerialNumber().toUpperCase());

        Device result = transactionManger.execute(() -> deviceDAO.update(device));
        transactionManger.closeManager();

        return DeviceConverter.convertToDTO(result);
    }



    @Override
    public ModelDTO findModel(long id) {
        Model model = transactionManger.execute(() -> modelDAO.find(id));
        ModelDTO modelDTO = ModelConverter.convertToDTO(model);

        transactionManger.closeManager();
        return modelDTO;
    }

    @Override
    public BrandDTO findBrand(long id) {
        Brand brand = transactionManger.execute(() -> brandDAO.find(id));
        BrandDTO brandDTO = BrandConverter.convertToDTO(brand);

        transactionManger.closeManager();
        return brandDTO;
    }

    @Override
    public RepairTypeDTO findRepairType(long id) {
        RepairType type = transactionManger.execute(() -> repairTypeDAO.find(id));
        RepairTypeDTO repairTypeDTO = RepairTypeConverter.convertToDTO(type);

        transactionManger.closeManager();
        return repairTypeDTO;
    }

    @Override
    public List<ModelDTO> findModelsByBrandId(long brandId) {
        List<Model> models = transactionManger.execute(() -> modelDAO.findAllByBrandId(brandId));
        List<ModelDTO> modelDTOList = ModelConverter.convertListToDTO(models);

        transactionManger.closeManager();
        return modelDTOList;
    }

    @Override
    public RepairDTO findRepair(long id) {
        Repair repair = transactionManger.execute(() -> repairDAO.find(id));
        RepairDTO repairDTO = RepairConverter.convertToDTO(repair);

        transactionManger.closeManager();
        return repairDTO;
    }

    @Override
    public ListForPage<RepairDTO> findRepairs(int pageNumber) {
        ListForPage<RepairDTO> result;

        Supplier<ListForPage<RepairDTO>> find = () -> {
            List<Repair> repairs = repairDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) repairDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<RepairDTO> list = RepairConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, new ArrayList<>());
        };

        result = transactionManger.execute(find);
        transactionManger.closeManager();
        return result;
    }

    @Override
    public ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber) {
        ListForPage<RepairDTO> result;

        Supplier<ListForPage<RepairDTO>> find = () -> {
            List<Repair> repairs = repairDAO.findRepairsByStatus(status, pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) repairDAO.getNumberOfEntriesByStatus(status).intValue()) / LIST_SIZE);
            List<RepairDTO> list = RepairConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, new ArrayList<>());
        };

        result = transactionManger.execute(find);
        transactionManger.closeManager();
        return result;
    }

    @Override
    public DeviceDTOResp findDevice(long id) {
        Device device = transactionManger.execute(() -> deviceDAO.find(id));
        DeviceDTOResp deviceDTOResp = DeviceConverter.convertToDTO(device);

        transactionManger.closeManager();
        return deviceDTOResp;
    }

    @Override
    public List<RepairTypeDTO> findRepairTypes() {
        return transactionManger.execute(() ->
                RepairTypeConverter.convertListToDTO(repairTypeDAO.findAll()));
    }

}
