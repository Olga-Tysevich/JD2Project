package it.academy.services.impl;


import it.academy.dao.repair.RepairCategoryDAO;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairCategoryImpl;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair.RepairCategoryDTOReq;
import it.academy.dto.req.repair.RepairTypeDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairType;
import it.academy.services.RepairService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.repair.RepairCategoryConverter;
import it.academy.utils.services.converters.repair.RepairTypeConverter;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class RepairServiceImpl implements RepairService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl();
    private RepairCategoryDAO repairCategoryDAO = new RepairCategoryImpl();



    @Override
    public RespDTO<RepairTypeDTOReq> addRepairType(RepairTypeDTOReq req) {
        RepairType type = ExceptionManager.tryExecute(() -> RepairTypeConverter.convertToEntity(req));
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
        RepairType type = ExceptionManager.tryExecute(() -> RepairTypeConverter.convertToEntity(req));
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
    public RespDTO<RepairCategoryDTOReq> addRepairCategory(RepairCategoryDTOReq req) {
        RepairCategory type = ExceptionManager.tryExecute(() -> RepairCategoryConverter.convertToEntity(req));
        Supplier<RepairCategory> save = () -> repairCategoryDAO.create(type);
        RespDTO<RepairCategoryDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> RepairCategoryConverter.convertToDTOReq(transactionManger.execute(save)));

        if (type != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, type.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<RepairCategoryDTOReq> changeRepairCategory(RepairCategoryDTOReq req) {
        RepairCategory type = ExceptionManager.tryExecute(() -> RepairCategoryConverter.convertToEntity(req));
        Supplier<RepairCategory> update = () -> repairCategoryDAO.update(type);
        RespDTO<RepairCategoryDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> RepairCategoryConverter.convertToDTOReq(transactionManger.execute(update)));

        if (type != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, type.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairCategoryDTOReq> findRepairCategories() {
        List<RepairCategory> result = transactionManger.execute(repairCategoryDAO::findAll);

        RespListDTO<RepairCategoryDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairCategoryConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairCategoryDTOReq> findRepairCategories(int pageNumber, int listSize) {
        List<RepairCategory> result = transactionManger.execute(() -> repairCategoryDAO.findForPage(pageNumber, listSize));

        RespListDTO<RepairCategoryDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairCategoryConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairCategoryDTOReq> findRepairCategories(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<RepairCategory> result = transactionManger.execute(() ->
                repairCategoryDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<RepairCategoryDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairCategoryConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
