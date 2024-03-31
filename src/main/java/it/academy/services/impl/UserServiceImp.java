package it.academy.services.impl;

import it.academy.dao.account.PermissionDAO;
import it.academy.dao.account.impl.PermissionDAOImpl;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.dto.req.device.ModelDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.role.Permission;
import it.academy.entities.device.components.Model;
import it.academy.services.UserService;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.device.ModelConverter;
import it.academy.utils.services.converters.accounts.PermissionConverter;
import java.math.BigInteger;
import java.util.List;

public class UserServiceImp implements UserService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private ModelDAO modelDAO = new ModelDAOImpl();


    @Override
    public RespDTO<BigInteger> getNumberOfEntries() {
        return ExceptionManager.getObjectFindResult(() -> transactionManger.execute(() -> modelDAO.getNumberOfEntries()));
    }

    @Override
    public RespListDTO<PermissionDTOReq> findPermissions() {
        List<Permission> result = transactionManger.execute(() -> permissionDAO.findAll());
        return ExceptionManager.getListSearchResult(() -> PermissionConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<PermissionDTOReq> findPermissions(int pageNumber, int listSize) {
        List<Permission> result = transactionManger.execute(() -> permissionDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> PermissionConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<PermissionDTOReq> findPermissions(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Permission> result = transactionManger.execute(() ->
                permissionDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        return ExceptionManager.getListSearchResult(() -> PermissionConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels() {
        List<Model> result = transactionManger.execute(() -> modelDAO.findAll());
        return ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels(int pageNumber, int listSize) {
        List<Model> result = transactionManger.execute(() -> modelDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Model> result = transactionManger.execute(() ->
                modelDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        return ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
    }
}
