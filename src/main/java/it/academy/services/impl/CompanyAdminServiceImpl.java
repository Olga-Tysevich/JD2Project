package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.PermissionDAO;
import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.PermissionDAOImpl;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.services.CompanyAdminService;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.services.Converter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class CompanyAdminServiceImpl implements CompanyAdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public RespDTO createRole(RoleDTOReq req) {

        Set<Permission> permissionSet = req.getPermissions();
        permissionSet.forEach(p -> permissionDAO.findByAllParameters(
                List.of(new ParameterContainer<>(PERMISSION_TYPE, p.getType(), true),
                        new ParameterContainer<>(PERMISSION_CATEGORY, p.getCategory(), true)))
                .stream()
                .findFirst()
                .ifPresentOrElse(
                        (permission) -> p.setId(permission.getId()),
                        () -> {
                            Supplier<Permission> create = () -> permissionDAO.create(p);
                            transactionManger.execute(create);
                        }
                )
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
    public RespListDTO<RoleDTOReq> findAllRoles() {
        List<Role> result = transactionManger.execute(roleDAO::findAll);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, RoleDTOReq.class));
    }

    @Override
    public RespListDTO<RoleDTOReq> findAllRoles(ParametersForSearchDTO parameters) {
        Map<String, Boolean> filtersMap = new HashMap<>();
        parameters.getFilters()
                .forEach(f -> filtersMap.put(f, true));
        return findAllForPage(filtersMap, parameters, RoleDTOReq.class);
    }

    @Override
    public RespListDTO<RoleDTOReq> findAllRoles(int pageNumber, int listSize) {
        List<Role> result = transactionManger.execute(() -> roleDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, RoleDTOReq.class));
    }

    @Override
    public RespListDTO<AccountDTOReq> findAllAccounts() {
        List<Account> result = transactionManger.execute(accountDAO::findAll);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

    @Override
    public RespListDTO<AccountDTOReq> findAllAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

    @Override
    public RespListDTO<AccountDTOReq> findAllAccounts(ParametersForSearchDTO parameters) {
        Map<String, Boolean> filtersMap = new HashMap<>();
        parameters.getFilters()
                .forEach(f -> filtersMap.put(f, true));
        return findAllForPage(filtersMap, parameters, AccountDTOReq.class);
    }

    @Override
    public RespListDTO<AccountDTOReq> findAllBlockedAccounts() {
        Supplier<List<Account>> findBlockedAccounts = () ->
                accountDAO.findByAnyMatch(
                        List.of(new ParameterContainer<>(IS_ACTIVE_ACCOUNT, false, true))
                );
        List<Account> result = transactionManger.execute(findBlockedAccounts);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

    @Override
    public RespListDTO<AccountDTOReq> findAllBlockedAccounts(int pageNumber, int listSize) {
        Supplier<List<Account>> findBlockedAccounts = () ->
                accountDAO.findByAllParameters(pageNumber, listSize,
                        List.of(new ParameterContainer<>(IS_ACTIVE_ACCOUNT, false, true))
                );

        List<Account> result = transactionManger.execute(findBlockedAccounts);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

    @Override
    public RespListDTO<AccountDTOReq> findAllBlockedAccounts(ParametersForSearchDTO parameters) {
        Map<String, Boolean> filtersMap = new HashMap<>();
        parameters.getFilters()
                .forEach(f -> filtersMap.put(f, true));
        filtersMap.put(IS_ACTIVE_ACCOUNT, true);

        parameters.setUserInput(parameters.getUserInput() + false);
        return findAllForPage(filtersMap, parameters, AccountDTOReq.class);
    }

    private <T> RespListDTO<T> findAllForPage(Map<String, Boolean> filtersMap,
                                              ParametersForSearchDTO parameters,
                                              Class<T> resultClass) {

        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(filtersMap, parameters.getUserInput());
        List<Account> result = transactionManger.execute(() ->
                accountDAO.findByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, resultClass));
    }

}
