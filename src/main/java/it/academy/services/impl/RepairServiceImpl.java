package it.academy.services.impl;

import it.academy.dao.*;
import it.academy.dao.impl.*;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.ChangeRepairDTO;
import it.academy.dto.resp.*;
import it.academy.entities.*;
import it.academy.exceptions.model.BrandsNotFound;
import it.academy.exceptions.model.ModelNotFound;
import it.academy.services.RepairService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.*;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.LIST_SIZE;
import static it.academy.utils.Constants.SERIAL_NUMBER;

public class RepairServiceImpl implements RepairService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();
    private final RepairDAO repairDAO = new RepairDAOImpl();
    private final RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl();
    private final BrandDAO brandDAO = new BrandDAOImpl();
    private final DeviceDAO deviceDAO = new DeviceDAOImpl();
    private final ModelDAO modelDAO = new ModelDAOImpl();

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

        return RepairFormDTO.builder()
                .serviceCenters(serviceCenters)
                .currentBrandId(brandId)
                .brands(brands)
                .models(modelsForBrand)
                .build();
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

        repairDAO.create(repair);

        transactionManger.commit();
    }

    @Override
    public void updateRepair(RepairDTO repairDTO) {

        Repair repair = RepairConverter.convertToEntity(repairDTO);
        transactionManger.beginTransaction();
        Device device = deviceDAO.findByUniqueParameter(SERIAL_NUMBER, repairDTO.getSerialNumber());
        ServiceCenter serviceCenter = serviceCenterDAO.find(repairDTO.getServiceCenterId());
        repair.setServiceCenter(serviceCenter);
        repair.setDevice(device);

        repairDAO.update(repair);

        transactionManger.commit();
    }

    @Override
    public ChangeRepairFormDTO findRepair(AccountDTO currentAccount, long id) {

        ServiceHelper.checkCurrentAccount(currentAccount);

        transactionManger.beginTransaction();
        RepairDTO repairDTO = RepairConverter.convertToRepairDTO(repairDAO.find(id));
        RepairFormDTO repairFormDTO = getRepairFormData(currentAccount, repairDTO.getBrandId());

        ChangeRepairFormDTO result = new ChangeRepairFormDTO(repairDTO, repairFormDTO);
        transactionManger.closeManager();

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
        Supplier<ListForPage<ChangeRepairDTO>> find = () -> ServiceHelper.getList(repairDAO,
                () -> repairDAO.findRepairsByStatus(status, pageNumber, LIST_SIZE),
                pageNumber,
                RepairConverter::convertToDTOList,
                FilterManager::getFiltersForServiceCenter);
        return transactionManger.execute(find);
    }

    @Override
    public List<RepairTypeDTO> findRepairTypes() {
        return transactionManger.execute(() ->
                RepairTypeConverter.convertListToDTO(repairTypeDAO.findAll()));
    }

}
