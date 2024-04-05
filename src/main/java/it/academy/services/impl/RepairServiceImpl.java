package it.academy.services.impl;


import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.Model;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.utils.Builder;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.converters.repair.RepairConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import static it.academy.utils.Constants.LIST_SIZE;
import static it.academy.utils.Constants.SERIAL_NUMBER;

public class RepairServiceImpl implements RepairService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairDAO repairDAO = new RepairDAOImpl();
    private BrandDAO brandDAO = new BrandDAOImpl();
    private DeviceDAO deviceDAO = new DeviceDAOImpl();
    private ModelDAO modelDAO = new ModelDAOImpl();

    @Override
    public void addRepair(RepairDTO repairDTO) {
        Repair repair = RepairConverter.convertDTOToEntity(repairDTO);

        transactionManger.execute(() -> repairDAO.create(repair));
    }


    @Override
    public List<BrandDTO> findBrands() {
        List<Brand> result = transactionManger.execute(brandDAO::findAll);
        List<BrandDTO> resp = BrandConverter.convertListToDTO(result);

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public DeviceDTO addDevice(DeviceDTO deviceDTO) {
        Device device = DeviceConverter.convertDTOToEntity(deviceDTO);
        device.setSerialNumber(device.getSerialNumber().toUpperCase());
        Device result = deviceDAO.findByUniqueParameter(SERIAL_NUMBER, device.getSerialNumber());
        if (result == null) {
            result = transactionManger.execute(() -> deviceDAO.create(device));
        }

        transactionManger.closeManager();
        assert result != null;
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
    public List<ModelDTO> findModelsByBrandId(long brandId) {
        List<Model> models = transactionManger.execute(() -> modelDAO.findAllByBrandId(brandId));
        List<ModelDTO> modelDTOList = ModelConverter.convertListToDTO(models);

        transactionManger.closeManager();
        return modelDTOList;
    }

    @Override
    public ListForPage<RepairDTO> findRepairs(int pageNumber) {
        List<Repair> repairs = transactionManger.execute(() -> repairDAO.findForPage(pageNumber, LIST_SIZE));

        List<RepairDTO> list = RepairConverter.convertListToDTO(repairs);
        int maxPageNumber = calculateMaxPageNumber();

        transactionManger.closeManager();
        return Builder.buildListForPage(list, pageNumber, maxPageNumber, new ArrayList<>());
    }

    @Override
    public ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber) {
        List<Repair> repairs = transactionManger.execute(() -> repairDAO.findRepairsByStatus(status, pageNumber, LIST_SIZE));

        List<RepairDTO> list = RepairConverter.convertListToDTO(repairs);
        int maxPageNumber = calculateMaxPageNumber();

        transactionManger.closeManager();
        return Builder.buildListForPage(list, pageNumber, maxPageNumber, new ArrayList<>());
    }

    private int calculateMaxPageNumber() {
        return (int) Math.ceil(((double) transactionManger.execute(() ->
                repairDAO.getNumberOfEntries().intValue()) / LIST_SIZE));
    }

}
