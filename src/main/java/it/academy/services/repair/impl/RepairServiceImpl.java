package it.academy.services.repair.impl;

import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.account.impl.ServiceCenterDAOImpl;
import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.dao.spare_part.impl.SparePartOrderDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.*;
import it.academy.dto.spare_part.SparePartOrderDTO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.device.Brand;
import it.academy.entities.device.Device;
import it.academy.entities.device.Model;
import it.academy.entities.repair.Repair;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.converters.repair.RepairConverter;
import it.academy.utils.converters.spare_part.SparePartOrderConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class RepairServiceImpl implements RepairService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);
    private final RepairDAO repairDAO = new RepairDAOImpl(transactionManger);
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);
    private final DeviceDAO deviceDAO = new DeviceDAOImpl(transactionManger);
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final SparePartOrderDAO sparePartOrderDAO = new SparePartOrderDAOImpl(transactionManger);

    @Override
    public RepairFormDTO getRepairFormData(long brandId) {
        Supplier<RepairFormDTO> get = () -> {

            if (brandDAO.getNumberOfEntries() == 0) {
                log.warn(OBJECTS_NOT_FOUND_PATTERN, Brand.class);
                throw new BrandsNotFound();
            }

            if (modelDAO.getNumberOfEntries() == 0) {
                log.warn(OBJECTS_NOT_FOUND_PATTERN, Model.class);
                throw new ModelNotFound();
            }

            List<BrandDTO> brands = BrandConverter.convertToDTOList(brandDAO.findBrandsWithModels());
            List<ModelDTO> modelsForBrand = ModelConverter.convertToDTOList(modelDAO.findAllByBrandId(brandId));
            Map<Long, String> serviceCenters = serviceCenterDAO.findAll().stream()
                    .collect(Collectors.toMap(ServiceCenter::getId, ServiceCenter::getServiceName));

            RepairFormDTO repairFormDTO = RepairFormDTO.builder()
                    .serviceCenters(serviceCenters)
                    .currentBrandId(brandId)
                    .selectedBrandId(brandId)
                    .brands(brands)
                    .models(modelsForBrand)
                    .build();
            log.info(OBJECT_CREATED_PATTERN, repairFormDTO);
            return repairFormDTO;
        };
        return transactionManger.execute(get);
    }

    @Override
    public void addRepair(CreateRepairDTO repairDTO) {

        Supplier<Repair> create = () -> {
            Repair repair = RepairConverter.convertToEntity(repairDTO);

            long serviceCenterId = repairDTO.getServiceCenterId();
            String serialNumber = repairDTO.getSerialNumber();
            ServiceCenter serviceCenter = serviceCenterDAO.find(serviceCenterId);
            Device device = deviceDAO.findByUniqueParameter(SERIAL_NUMBER, serialNumber);

            if (serviceCenter == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, serviceCenterId, ServiceCenter.class);
                throw new ObjectNotFound(SERVICE_CENTER_NOT_FOUND);
            }

            if (device == null) {
                device = repair.getDevice();
                Model model = modelDAO.find(repairDTO.getModelId());
                device.setModel(model);
                deviceDAO.create(device);
                log.info(OBJECT_CREATED_PATTERN, device);
            }

            repair.setServiceCenter(serviceCenter);
            repairDAO.create(repair);
            log.info(OBJECT_CREATED_PATTERN, repair);
            return repair;
        };

        transactionManger.execute(create);
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
    public ChangeRepairFormDTO findRepair( long id) {

        transactionManger.beginTransaction();
        Repair repair = repairDAO.find(id);
        RepairDTO repairDTO = RepairConverter.convertToRepairDTO(repair);
        RepairFormDTO repairFormDTO = getRepairFormData(repairDTO.getBrandId());
        DeviceDTO device = DeviceConverter.convertToDTO(repair.getDevice());
        repairFormDTO.setDevice(device);
        List<SparePartOrder> orders = sparePartOrderDAO.findSparePartOrdersByRepairId(repair.getId());
        List<SparePartOrderDTO> ordersDTO = SparePartOrderConverter.convertListToDTO(orders);

        ChangeRepairFormDTO result = new ChangeRepairFormDTO(repairDTO, repairFormDTO, ordersDTO);

        transactionManger.commit();

        return result;
    }

    @Override
    public ListForPage<RepairForTableDTO> findRepairs(int pageNumber) {
        long numberOfEntries = repairDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(
                () -> repairDAO.findForPage(pageNumber, LIST_SIZE),
                pageNumber,
                maxPageNumber);
    }

    @Override
    public ListForPage<RepairForTableDTO> findRepairs(int pageNumber, String filter, String userInput) {
        long numberOfEntries = repairDAO.getNumberOfEntriesByFilter(filter, userInput);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(
                () -> repairDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, userInput),
                pageNumber,
                maxPageNumber);
    }

    @Override
    public ListForPage<RepairForTableDTO> findRepairsByStatus(RepairStatus status, int pageNumber) {
        long numberOfEntries = repairDAO.getNumberOfEntriesByStatus(status).intValue();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(
                () -> repairDAO.findRepairsByStatus(status, pageNumber, LIST_SIZE),
                pageNumber,
                maxPageNumber);
    }

    private ListForPage<RepairForTableDTO> find(Supplier<List<Repair>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<Repair> repairs = ServiceHelper.getList(transactionManger, methodForSearch, Repair.class);
        List<EntityFilter> filters = FilterManager.getFiltersForRepair();
        List<RepairForTableDTO> repairsDTO = RepairConverter.convertToDTOList(repairs);
        return Builder.buildListForPage(repairsDTO, pageNumber, maxPageNumber, filters);
    }

}
