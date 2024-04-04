package it.academy.services.repair.impl;


import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.repair.RepairCategoryDAO;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairCategoryImpl;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.repair_page_N.RepairPageDataDTO;
import it.academy.dto.req.device.BrandDTO;
import it.academy.dto.req.device.ModelDTO;
import it.academy.dto.req.repair.CreateRepairDTO;
import it.academy.dto.req.repair.RepairCategoryDTO;
import it.academy.dto.req.repair.RepairDTO;
import it.academy.dto.req.repair.RepairTypeDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Buyer;
import it.academy.entities.device.components.Model;
import it.academy.entities.device.components.Salesman;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairType;
import it.academy.services.repair.RepairService;
import it.academy.utils.MessageManager;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.ModelConverter;
import it.academy.utils.converters.repair.RepairConverter;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.ExceptionManager;
import it.academy.utils.converters.repair.RepairCategoryConverter;
import it.academy.utils.converters.repair.RepairTypeConverter;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class RepairServiceImpl implements RepairService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl();
    private RepairCategoryDAO repairCategoryDAO = new RepairCategoryImpl();
    private RepairDAO repairDAO = new RepairDAOImpl();
    private BrandDAO brandDAO = new BrandDAOImpl();
    private ModelDAO modelDAO = new ModelDAOImpl();
    private DeviceDAO deviceDAO = new DeviceDAOImpl();

    @Override
    public RepairPageDataDTO getDataForPage(long brandId) {

        Supplier<RepairPageDataDTO> find = () -> {
            List<RepairCategoryDTO> repairCategory = RepairCategoryConverter.convertListToDTO(repairCategoryDAO.findAll());
            List<BrandDTO> brands = BrandConverter.convertListToDTO(brandDAO.findAll());
            BrandDTO currentBrand = BrandConverter.convertToDTO(brandDAO.find(brandId));
            List<ModelDTO> models = ModelConverter.convertListToDTO(modelDAO.findAllByBrandId(currentBrand.getId()));

           return RepairPageDataDTO.builder()
                    .brands(brands)
                    .categories(repairCategory)
                    .currentBrand(currentBrand)
                   .models(models)
                    .build();
        };

        return transactionManger.execute(find);
    }

    @Override
    public RepairDTO addRepair(CreateRepairDTO req) {

        Supplier<Repair> create = () -> {
            Model model = modelDAO.find(req.getModelId());
            Salesman salesman = Salesman.builder()
                    .name(req.getSalesmanName())
                    .phone(req.getSalesmanPhone())
                    .build();
            Buyer buyer = Buyer.builder()
                    .name(req.getBuyerName())
                    .surname(req.getBuyerSurname())
                    .phone(req.getSalesmanPhone())
                    .build();
            Device device = Device.builder()
                    .model(model)
                    .serialNumber(req.getSerialNumber())
                    .dateOfSale(req.getDateOfSale())
                    .salesman(salesman)
                    .buyer(buyer)
                    .build();

            RepairCategory category = repairCategoryDAO.find(req.getCategoryId());
            //TODO обавить прверку по серийнику и разобраться с сервисомб выявленным дефектом и остальныи налами
            device = deviceDAO.create(device);

            Repair repair = Repair.builder()
                    .serviceRepairNumber(req.getServiceRepairNumber())
                    .defectDescription(req.getDefectDescription())
                    .category(category)
                    .status(req.getStatus())
                    .device(device)
                    .isDeleted(false)
                    .build();

            return repairDAO.create(repair);
        };

        Repair r = transactionManger.execute(create);
//        RepairDTO result = RepairConverter.convertToDTO(r);
        transactionManger.closeManager();
        return null;
    }


    @Override
    public List<RepairCategoryDTO> findRepairCategories() {
        List<RepairCategory> result = transactionManger.execute(repairCategoryDAO::findAll);

        List<RepairCategoryDTO> resp =RepairCategoryConverter.convertListToDTO(result);
        transactionManger.closeManager();
        return resp;
    }


    @Override
    public RespDTO<RepairTypeDTOReq> addRepairType(RepairTypeDTOReq req) {
        RepairType type = ExceptionManager.tryExecute(() -> RepairTypeConverter.convertDTOReqToEntity(req));
        Supplier<RepairType> save = () -> repairTypeDAO.create(type);
        RespDTO<RepairTypeDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> RepairTypeConverter.convertToDTOReq(transactionManger.execute(save)));

        if (type != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, type.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<RepairTypeDTOReq> changeRepairType(RepairTypeDTOReq req) {
        RepairType type = ExceptionManager.tryExecute(() -> RepairTypeConverter.convertDTOReqToEntity(req));
        Supplier<RepairType> update = () -> repairTypeDAO.update(type);
        RespDTO<RepairTypeDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> RepairTypeConverter.convertToDTOReq(transactionManger.execute(update)));


        if (type != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, type.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairTypeDTOReq> findRepairTypes() {
        List<RepairType> result = transactionManger.execute(repairTypeDAO::findAll);

        RespListDTO<RepairTypeDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairTypeConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairTypeDTOReq> findRepairTypes(int pageNumber, int listSize) {
        List<RepairType> result = transactionManger.execute(() -> repairTypeDAO.findForPage(pageNumber, listSize));

        RespListDTO<RepairTypeDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairTypeConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairTypeDTOReq> findRepairTypes(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<RepairType> result = transactionManger.execute(() ->
                repairTypeDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<RepairTypeDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairTypeConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<RepairCategoryDTO> addRepairCategory(RepairCategoryDTO req) {
        RepairCategory type = ExceptionManager.tryExecute(() -> RepairCategoryConverter.convertDTOToEntity(req));
        Supplier<RepairCategory> save = () -> repairCategoryDAO.create(type);
        RespDTO<RepairCategoryDTO> resp = ExceptionManager.getObjectSaveResult(() -> RepairCategoryConverter.convertToDTO(transactionManger.execute(save)));

        if (type != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, type.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<RepairCategoryDTO> changeRepairCategory(RepairCategoryDTO req) {
        RepairCategory type = ExceptionManager.tryExecute(() -> RepairCategoryConverter.convertDTOToEntity(req));
        Supplier<RepairCategory> update = () -> repairCategoryDAO.update(type);
        RespDTO<RepairCategoryDTO> resp = ExceptionManager.getObjectSaveResult(() -> RepairCategoryConverter.convertToDTO(transactionManger.execute(update)));

        if (type != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, type.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairCategoryDTO> findRepairCategories(int pageNumber, int listSize) {
        List<RepairCategory> result = transactionManger.execute(() -> repairCategoryDAO.findForPage(pageNumber, listSize));

        RespListDTO<RepairCategoryDTO> resp = ExceptionManager.getListSearchResult(() -> RepairCategoryConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairCategoryDTO> findRepairCategories(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<RepairCategory> result = transactionManger.execute(() ->
                repairCategoryDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<RepairCategoryDTO> resp = ExceptionManager.getListSearchResult(() -> RepairCategoryConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<RepairDTO> changeRepair(RepairDTO req) {
        Repair repair = ExceptionManager.tryExecute(() -> RepairConverter.convertDTOToEntity(req));
        Supplier<Repair> update = () -> repairDAO.update(repair);
        RespDTO<RepairDTO> resp = ExceptionManager.getObjectSaveResult(() -> RepairConverter.convertToDTO(transactionManger.execute(update)));

        if (repair != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, repair));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairDTO> findRepairs() {
        List<Repair> result = transactionManger.execute(repairDAO::findAll);

        RespListDTO<RepairDTO> resp = ExceptionManager.getListSearchResult(() -> RepairConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairDTO> findRepairs(int pageNumber, int listSize) {
        List<Repair> result = transactionManger.execute(() -> repairDAO.findForPage(pageNumber, listSize));

        RespListDTO<RepairDTO> resp = ExceptionManager.getListSearchResult(() -> RepairConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairDTO> findRepairs(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Repair> result = transactionManger.execute(() ->
                repairDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<RepairDTO> resp = ExceptionManager.getListSearchResult(() -> RepairConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }
}
