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
import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.*;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.device.Brand;
import it.academy.entities.device.Device;
import it.academy.entities.device.Model;
import it.academy.entities.repair.Repair;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.impl.DeviceConverter;
import it.academy.utils.converters.impl.BrandConverter;
import it.academy.utils.converters.impl.ModelConverter;
import it.academy.utils.converters.impl.RepairConverter;
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
    private final BrandConverter brandConverter = new BrandConverter();
    private final DeviceDAO deviceDAO = new DeviceDAOImpl(transactionManger);
    private final ModelDAO modelDAO = new ModelDAOImpl(transactionManger);
    private final ModelConverter modelConverter = new ModelConverter();
    private final DeviceConverter deviceConverter = new DeviceConverter();
    private final RepairConverter repairConverter = new RepairConverter();
    private final ServiceHelper<Repair, RepairDTO> repairHelper =
            new ServiceHelper<>(repairDAO, Repair.class, repairConverter, transactionManger);


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

            List<BrandDTO> brands = brandConverter.convertToDTOList(brandDAO.findBrandsWithModels());
            List<ModelDTO> modelsForBrand = modelConverter.convertToDTOList(modelDAO.findAllByBrandId(brandId));
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
            Repair repair = repairConverter.convertToEntity(repairDTO);

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

        Repair repair = repairConverter.convertToEntity(repairDTO);

        transactionManger.beginTransaction();
        ServiceCenter serviceCenter = serviceCenterDAO.find(repairDTO.getServiceCenterId());
        repair.setServiceCenter(serviceCenter);

        try {
            repairDAO.update(repair);
            log.info(OBJECT_CREATED_PATTERN, repair);
        } catch (Exception e) {
            log.error(ERROR_PATTERN, e.getMessage(), repair);
            throw e;
        }

        transactionManger.commit();
    }

    @Override
    public ChangeRepairFormDTO findRepair(long id) {

        transactionManger.beginTransaction();
        Repair repair = repairDAO.find(id);
        RepairDTO repairDTO = repairConverter.convertToDTO(repair);
        long modelId = repair.getDevice().getModel().getId();
        RepairFormDTO repairFormDTO = getRepairFormData(modelId);
        DeviceDTO device = deviceConverter.convertToDTO(repair.getDevice());
        repairFormDTO.setDevice(device);
        ChangeRepairFormDTO result = new ChangeRepairFormDTO(repairDTO, repairFormDTO);

        transactionManger.commit();

        return result;
    }

    @Override
    public ListForPage<RepairDTO> findRepairs(int pageNumber, String filter, String userInput) {
        return repairHelper.find(pageNumber, filter, userInput);
    }

    @Override
    public ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber) {
        List<Repair> result = repairDAO.findRepairsByStatus(status, pageNumber, LIST_SIZE);
        List<EntityFilter> filters = FilterManager.getFilters(Repair.class);
        List<RepairDTO> listDTO = repairConverter.convertToDTOList(result);
        long numberOfEntries = repairDAO.getNumberOfEntriesByStatus(status).intValue();
        int maxPageNumber = (int) Math.ceil(((double) numberOfEntries) / LIST_SIZE);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

}
