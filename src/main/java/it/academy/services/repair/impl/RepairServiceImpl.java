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
import it.academy.dto.TablePage2;
import it.academy.dto.device.BrandDTO;
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
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.impl.BrandConverter;
import it.academy.utils.converters.impl.ModelConverter;
import it.academy.utils.converters.impl.RepairConverter;
import it.academy.utils.converters.impl.SparePartConverter;
import it.academy.utils.converters.spare_part.SparePartOrderConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RepairStatus;
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
    private final RepairConverter repairConverter = new RepairConverter();
    private final SparePartOrderDAO sparePartOrderDAO = new SparePartOrderDAOImpl(transactionManger);

    @Override
    public RepairFormDTO getRepairForm(long brandId) {
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
            List<ModelDTO> modelsForBrand = modelConverter.convertToDTOList(modelDAO.findActiveByBrandId(brandId));
            Map<Long, String> serviceCenters = serviceCenterDAO.findAll().stream()
                    .collect(Collectors.toMap(ServiceCenter::getId, ServiceCenter::getServiceName));

            RepairFormDTO repairFormDTO = RepairFormDTO.builder()
                    .serviceCenters(serviceCenters)
                    .currentBrandId(brandId)
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
        transactionManger.execute(() -> {
            Repair repair = repairConverter.convertToEntity(repairDTO);

            ServiceCenter serviceCenter = serviceCenterDAO.find(repairDTO.getServiceCenterId());
            repair.setServiceCenter(serviceCenter);
            Model model = modelDAO.find(repairDTO.getModelId());
            repair.getDevice().setModel(model);

            try {
                repairDAO.update(repair);
                log.info(OBJECT_CREATED_PATTERN, repair);
            } catch (Exception e) {
                log.error(ERROR_PATTERN, e.getMessage(), repair);
                throw e;
            }
            return repair;
        });
    }

    @Override
    public RepairFormDTO findRepair(long id) {
        return transactionManger.execute(() -> {
            Repair repair = repairDAO.find(id);
            RepairDTO repairDTO = repairConverter.convertToDTO(repair);
            long brandId = repair.getDevice().getModel().getBrand().getId();
            RepairFormDTO repairFormDTO = getRepairForm(brandId);
            repairFormDTO.setRepairDTO(repairDTO);
            return repairFormDTO;
        });
    }

    @Override
    public UserRepairFormDTO findRepairForUser(long id) {
        return transactionManger.execute(() -> {
            Repair repair = repairDAO.find(id);
            RepairDTO repairDTO = repairConverter.convertToDTO(repair);
            List<SparePartOrderDTO> orderDTOList = null;

            if (RepairStatus.WAITING_FOR_SPARE_PARTS.equals(repair.getStatus())) {
                List<SparePartOrder> orders = sparePartOrderDAO.findSparePartOrdersByRepairId(id);
                orderDTOList = SparePartOrderConverter.convertToDTOList(orders);
            }

            return UserRepairFormDTO.builder()
                    .repairDTO(repairDTO)
                    .orders(orderDTOList)
                    .build();
        });
    }

    @Override
    public TablePage2<RepairDTO> findRepairs(int pageNumber) {
        Supplier<TablePage2<RepairDTO>> find = () -> {
            long numberOfEntries = repairDAO.getNumberOfEntries();
            List<Repair> repairs = repairDAO.findForPage(pageNumber, LIST_SIZE);
            List<RepairDTO> dtoList = repairConverter.convertToDTOList(repairs);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<RepairDTO> findRepairsByFilter(int pageNumber, String filter, String userInput) {
        Supplier<TablePage2<RepairDTO>> find = () -> {
            long numberOfEntries = repairDAO.getNumberOfEntriesByFilter(filter, userInput);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Repair> repairs = repairDAO.findForPageByAnyMatch(pageNumberForSearch, LIST_SIZE, filter, userInput);
            List<RepairDTO> dtoList = repairConverter.convertToDTOList(repairs);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber) {

        Supplier<TablePage2<RepairDTO>> find = () -> {
            long numberOfEntries = repairDAO.getNumberOfEntriesByStatus(status);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Repair> repairs = repairDAO.findRepairsByStatus(status, pageNumberForSearch, LIST_SIZE);
            List<RepairDTO> dtoList = repairConverter.convertToDTOList(repairs);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return status != null ? transactionManger.execute(find) : findRepairs(pageNumber);
    }

    @Override
    public TablePage2<RepairDTO> findRepairsForUser(long serviceCenterId, int pageNumber) {
        Supplier<TablePage2<RepairDTO>> find = () -> {
            long numberOfEntries = repairDAO.getNumberOfEntriesByServiceId(serviceCenterId);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Repair> repairs = repairDAO.findRepairsByServiceId(serviceCenterId, pageNumberForSearch, LIST_SIZE);
            List<RepairDTO> dtoList = repairConverter.convertToDTOList(repairs);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<RepairDTO> findRepairsByFilterForUser(long serviceCenterId, int pageNumber, String filter, String userInput) {
        Supplier<TablePage2<RepairDTO>> find = () -> {
            long numberOfEntries = repairDAO.getNumberOfEntriesByFilterAndServiceId(serviceCenterId, filter, userInput);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Repair> repairs = repairDAO.findRepairsByFilterAndServiceId(serviceCenterId, pageNumberForSearch, LIST_SIZE, filter, userInput);
            List<RepairDTO> dtoList = repairConverter.convertToDTOList(repairs);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }


    @Override
    public TablePage2<RepairDTO> findRepairsByStatusForUser(long serviceCenterId, RepairStatus status, int pageNumber) {
        Supplier<TablePage2<RepairDTO>> find = () -> {
            long numberOfEntries = repairDAO.getNumberOfEntriesByStatusAndServiceId(serviceCenterId, status);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Repair> repairs = repairDAO.findRepairsByStatusAndServiceId(serviceCenterId, status, pageNumberForSearch, LIST_SIZE);
            List<RepairDTO> dtoList = repairConverter.convertToDTOList(repairs);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return status != null ? transactionManger.execute(find) : findRepairsForUser(serviceCenterId, pageNumber);
    }

}
