package it.academy.services.impl;

import it.academy.dao.*;
import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.impl.*;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.ChangeRepairDTO;
import it.academy.dto.resp.*;
import it.academy.entities.*;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.RepairService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.*;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class RepairServiceImpl implements RepairService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);
    private final RepairDAO repairDAO = new RepairDAOImpl(transactionManger);
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl(transactionManger);
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);
    private final DeviceDAO deviceDAO = new DeviceDAOImpl(transactionManger);
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final SparePartOrderDAO sparePartOrderDAO = new SparePartOrderDAOImpl(transactionManger);

    @Override
    public RepairFormDTO getRepairFormData(AccountDTO currentAccount, long brandId) {
        transactionManger.beginTransaction();
        List<BrandDTO> brands;
        List<ModelDTO> modelsForBrand;
        Map<Long, String> serviceCenters = null;

        if (RoleEnum.ADMIN.equals(currentAccount.getRole())) {
            brands = BrandConverter.convertToDTOList(brandDAO.findAll());
            modelsForBrand = ModelConverter.convertToDTOList(modelDAO.findAllByBrandId(brandId));
            serviceCenters = serviceCenterDAO.findAll().stream()
                    .collect(Collectors.toMap(ServiceCenter::getId, ServiceCenter::getServiceName));
        } else {
            brands = BrandConverter.convertToDTOList(brandDAO.findActiveObjects(true));
            modelsForBrand = ModelConverter.convertToDTOList(modelDAO.findActiveByBrandId(brandId));
        }

        if (brands == null || brands.isEmpty()) {
            throw new BrandsNotFound();
        }

        if (modelsForBrand == null || modelsForBrand.isEmpty()) {
            throw new ModelNotFound();
        }

        RepairFormDTO repairFormDTO = RepairFormDTO.builder()
                .serviceCenters(serviceCenters)
                .currentBrandId(brandId)
                .brands(brands)
                .models(modelsForBrand)
                .build();
        log.info(String.format(OBJECT_CREATED_PATTERN, repairFormDTO));
        transactionManger.commit();
        return repairFormDTO;
    }

    @Override
    public void addRepair(RepairDTO repairDTO) {

        Repair repair = RepairConverter.convertToEntity(repairDTO);
        transactionManger.beginTransaction();
        Device device = deviceDAO.findByUniqueParameter(SERIAL_NUMBER, repairDTO.getSerialNumber());

        if (device != null) {
            repair.setDevice(device);
        }

        device = repair.getDevice();
        Model model = modelDAO.find(repairDTO.getModelId());
        device.setModel(model);
        deviceDAO.create(device);
        ServiceCenter serviceCenter = serviceCenterDAO.find(repairDTO.getServiceCenterId());
        repair.setServiceCenter(serviceCenter);

        try {
            repairDAO.create(repair);
            log.info(String.format(OBJECT_CREATED_PATTERN, repair));
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), repair));
            throw e;
        }

        transactionManger.commit();
    }

    @Override
    public void updateRepair(RepairDTO repairDTO) {

        Repair repair = RepairConverter.convertToEntity(repairDTO);

        transactionManger.beginTransaction();

        Device device = deviceDAO.findByUniqueParameter(SERIAL_NUMBER, repairDTO.getSerialNumber());
        repair.getDevice().setId(device.getId());
        Model model = modelDAO.find(repairDTO.getModelId());
        device.setModel(model);
        repair.getDevice().setModel(device.getModel());
        ServiceCenter serviceCenter = serviceCenterDAO.find(repairDTO.getServiceCenterId());
        repair.setServiceCenter(serviceCenter);
        repair.setDevice(device);

        try {
            repairDAO.update(repair);
            log.info(String.format(OBJECT_CREATED_PATTERN, repair));
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), repair));
            throw e;
        }

        transactionManger.commit();
    }

    @Override
    public ChangeRepairFormDTO findRepair(AccountDTO currentAccount, long id) {

        ServiceHelper.checkCurrentAccount(currentAccount);

        transactionManger.beginTransaction();
        Repair repair = repairDAO.find(id);
        RepairDTO repairDTO = RepairConverter.convertToRepairDTO(repair);
        RepairFormDTO repairFormDTO = getRepairFormData(currentAccount, repairDTO.getBrandId());
        DeviceDTO device = DeviceConverter.convertToDTO(repair.getDevice());
        repairFormDTO.setDevice(device);
        List<SparePartOrder> orders = sparePartOrderDAO.findSparePartOrdersByRepairId(repair.getId());
        List<SparePartOrderDTO> ordersDTO = SparePartOrderConverter.convertListToDTO(orders);

        ChangeRepairFormDTO result = new ChangeRepairFormDTO(repairDTO, repairFormDTO, ordersDTO);

        transactionManger.commit();

        return result;
    }

    @Override
    public ListForPage<ChangeRepairDTO> findRepairs(int pageNumber) {
        Supplier<ListForPage<ChangeRepairDTO>> find = () -> ServiceHelper.getList(repairDAO,
                () -> repairDAO.findForPage(pageNumber, LIST_SIZE),
                pageNumber,
                RepairConverter::convertToDTOList,
                FilterManager::getFiltersForServiceCenter);
        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<ChangeRepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber) {

        Supplier<ListForPage<ChangeRepairDTO>> find = () -> {
            List<Repair> repairs = repairDAO.findRepairsByStatus(status, pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) repairDAO.getNumberOfEntriesByStatus(status).intValue()) / LIST_SIZE);
            List<ChangeRepairDTO> list = RepairConverter.convertToDTOList(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, new ArrayList<>());
        };

        return transactionManger.execute(find);
    }

    @Override
    public List<RepairTypeDTO> findRepairTypes() {
        return transactionManger.execute(() ->
                RepairTypeConverter.convertListToDTO(repairTypeDAO.findAll()));
    }

}
