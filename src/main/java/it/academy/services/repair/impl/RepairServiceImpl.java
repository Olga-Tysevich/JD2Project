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
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.dao.spare_part.impl.SparePartOrderDAOImpl;
import it.academy.dto.TablePage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.*;
import it.academy.dto.spare_part_order.SparePartOrderDTO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.ServiceCenter_;
import it.academy.entities.device.Brand;
import it.academy.entities.device.Device;
import it.academy.entities.device.Model;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.RepairType;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.repair.RepairService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.BrandConverter;
import it.academy.utils.converters.ModelConverter;
import it.academy.utils.converters.RepairConverter;
import it.academy.utils.converters.RepairTypeConverter;
import it.academy.utils.converters.SparePartOrderConverter;
import it.academy.utils.TransactionManger;
import it.academy.utils.enums.RepairStatus;
import lombok.extern.slf4j.Slf4j;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl(transactionManger);

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

            List<BrandDTO> brands = BrandConverter.convertToDTOList(brandDAO.findBrandsWithModels());
            List<ModelDTO> modelsForBrand = ModelConverter.convertToDTOList(modelDAO.findActiveByBrandId(brandId));
            List<RepairTypeDTO> repairTypes = RepairTypeConverter.convertToDTOList(repairTypeDAO.findAllActive());

            RepairFormDTO repairFormDTO = RepairFormDTO.builder()
                    .currentBrandId(brandId)
                    .brands(brands)
                    .models(modelsForBrand)
                    .repairTypes(repairTypes)
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
        transactionManger.execute(() -> {
            Repair repair = RepairConverter.convertToEntity(repairDTO);

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
    public void completeRepair(CompleteRepairDTO repairDTO) {
        Supplier<Repair> complete = () -> {
            Repair repair = repairDAO.find(repairDTO.getRepairId());
            RepairType repairType = repairTypeDAO.find(repairDTO.getRepairTypeId());
            repair.setRepairType(repairType);
            repair.setStatus(RepairStatus.COMPLETED);
            repair.setEndDate(Date.valueOf(LocalDate.now()));
            return repairDAO.update(repair);
        };
        transactionManger.execute(complete);
    }

    @Override
    public RepairFormDTO findRepair(long id) {
        return transactionManger.execute(() -> {
            Repair repair = repairDAO.find(id);
            RepairDTO repairDTO = RepairConverter.convertToDTO(repair);
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
            RepairStatus status = repair.getStatus();
            RepairDTO repairDTO = RepairConverter.convertToDTO(repair);
            List<SparePartOrderDTO> orderDTOList = null;
            List<RepairTypeDTO> repairTypes = null;

            if (RepairStatus.WAITING_FOR_SPARE_PARTS.equals(repair.getStatus())
                    || status.isFinishedStatus()) {
                List<SparePartOrder> orders = sparePartOrderDAO.findByRepairId(id);
                orderDTOList = SparePartOrderConverter.convertToDTOList(orders);
            }

            if (RepairStatus.CURRENT.equals(status)
                    || RepairStatus.WAITING_FOR_SPARE_PARTS.equals(status)) {
                List<RepairType> typeList = repairTypeDAO.findAllActive();
                repairTypes = RepairTypeConverter.convertToDTOList(typeList);
            }

            return UserRepairFormDTO.builder()
                    .repairDTO(repairDTO)
                    .orders(orderDTOList)
                    .repairTypes(repairTypes)
                    .build();
        });
    }

    @Override
    public TablePage<RepairDTO> findForPage(int pageNumber, Map<String, String> userInput) {
        return find(pageNumber, userInput);
    }

    @Override
    public TablePage<RepairDTO> findForPageByUserId(long serviceCenterId, int pageNumber, Map<String, String> userInput) {
        userInput.put(ServiceCenter_.ID, String.valueOf(serviceCenterId));
        return find(pageNumber, userInput);
    }

    private TablePage<RepairDTO> find(int pageNumber, Map<String, String> userInput) {
        Supplier<TablePage<RepairDTO>> find = () -> {
            long numberOfEntries = repairDAO.getNumberOfEntries(userInput);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Repair> repairs = repairDAO.findAllByPageAndFilter(pageNumberForSearch, LIST_SIZE, userInput);
            List<RepairDTO> dtoList = RepairConverter.convertToDTOList(repairs);
            return new TablePage<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

}
