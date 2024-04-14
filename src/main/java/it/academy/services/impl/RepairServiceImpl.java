package it.academy.services.impl;


import it.academy.dao.*;
import it.academy.dao.impl.*;
import it.academy.dto.resp.RepairDTO;
import it.academy.dto.resp.RepairTypeDTO;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.resp.DeviceDTOResp;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.Device;
import it.academy.entities.Brand;
import it.academy.entities.Repair;
import it.academy.utils.enums.RepairStatus;
import it.academy.entities.RepairType;
import it.academy.services.RepairService;
import it.academy.utils.Builder;
import it.academy.utils.converters.BrandConverter;
import it.academy.utils.converters.DeviceConverter;
import it.academy.utils.converters.RepairConverter;
import it.academy.utils.converters.RepairTypeConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.LIST_SIZE;
import static it.academy.utils.Constants.SERIAL_NUMBER;

public class RepairServiceImpl implements RepairService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final RepairDAO repairDAO = new RepairDAOImpl();
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl();
    private final BrandDAO brandDAO = new BrandDAOImpl();
    private final DeviceDAO deviceDAO = new DeviceDAOImpl();
    private final ModelDAO modelDAO = new ModelDAOImpl();

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
            return BrandConverter.convertToDTOList(result);
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


//
//    @Override
//    public CreateModelDTO findModel(long id) {
//        Model model = transactionManger.execute(() -> modelDAO.find(id));
//        CreateModelDTO createModelDTO = ModelConverter.convertToDTO(model);
//
//        transactionManger.closeManager();
//        return createModelDTO;
//    }

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

//    @Override
//    public List<CreateModelDTO> findModelsByBrandId(long brandId) {
//        List<Model> models = transactionManger.execute(() -> modelDAO.findAllByBrandId(brandId));
//        List<CreateModelDTO> createModelDTOList = ModelConverter.convertToDTOList(models);
//
//        transactionManger.closeManager();
//        return createModelDTOList;
//    }

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
