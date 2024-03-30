package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.PermissionDAO;
import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.ServiceAccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.PermissionDAOImpl;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dao.account.impl.ServiceAccountDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.services.CompanyAdminService;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.services.converters.AccountConverter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.converters.RoleConverter;

import java.util.*;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class CompanyAdminServiceImpl implements CompanyAdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);
    private ServiceAccountDAO serviceAccountDAO = new ServiceAccountDAOImpl();

    @Override
    public RespDTO createRole(RoleDTOReq req) {

        Set<Permission> permissionSet = req.getPermissions();
        permissionSet.forEach(p -> {
                    Permission permission = permissionDAO.findByCategoryAndType(
                            List.of(new ParameterContainer<>(PERMISSION_TYPE, p.getType()),
                                    new ParameterContainer<>(PERMISSION_CATEGORY, p.getCategory())));
                    if (permission == null) {
                        ExceptionManager.tryExecute(() -> transactionManger.execute(() -> permissionDAO.create(p)));
                    }
                }
        );

        Role role = Role.builder()
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();

        Supplier<Role> find = () -> {
            Role result = roleDAO.findByUniqueParameter(ROLE_NAME, req.getName());
            if (result == null) {
                result = roleDAO.create(role);
            }
            return result;
        };

        return ExceptionManager.getObjectSaveResult(() -> transactionManger.execute(find));
    }

    @Override
    public RespListDTO<RoleDTOReq> findRoles() {
        List<Role> result = transactionManger.execute(roleDAO::findAll);
        return ExceptionManager.getListSearchResult(() -> RoleConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<RoleDTOReq> findRoles(ParametersForSearchDTO parameters) {
        List<String> filters = new ArrayList<>(parameters.getFilters());

        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(filters, parameters.getUserInput());
        List<Role> result = transactionManger.execute(() ->
                roleDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        return ExceptionManager.getListSearchResult(() -> RoleConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<RoleDTOReq> findRoles(int pageNumber, int listSize) {
        List<Role> result = transactionManger.execute(() -> roleDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> RoleConverter.convertListToDTOReq(result));
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts() {
        List<Account> result = transactionManger.execute(accountDAO::findAll);
        return ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Account> result = transactionManger.execute(() ->
                accountDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        return ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts() {
        List<Account> result = transactionManger.execute(() -> accountDAO.findBlockedAccounts());
        return ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findBlockedAccountsForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Account> result = transactionManger.execute(() ->
                accountDAO.findBlockedAccountsByParameters(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        return ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts() {
        return null;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(int pageNumber, int listSize) {
        return null;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(ParametersForSearchDTO parameters) {
        return null;
    }

}
