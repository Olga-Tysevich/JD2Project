package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.PermissionDAO;
import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.PermissionDAOImpl;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.DTOResp;
import it.academy.dto.resp.DTORespList;
import it.academy.entities.account.Account;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.services.CompanyAdminService;
import it.academy.utils.services.Converter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class CompanyAdminServiceImpl implements CompanyAdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public DTOResp createRole(RoleDTOReq req) {

        Set<Permission> permissionSet = req.getPermissions();
        permissionSet.forEach(p -> permissionDAO.findAllByParameters(
                List.of(new ParameterContainer<>(PERMISSION_TYPE, p.getType().name(), true),
                        new ParameterContainer<>(PERMISSION_CATEGORY, p.getCategory().name(), true)))
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
    public DTORespList<RoleDTOReq> findAllRoles() {
        List<Role> result = transactionManger.execute(roleDAO::findAll);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, RoleDTOReq.class));
    }

    @Override
    public DTORespList<RoleDTOReq> findAllRolesForPage(int pageNumber, int listSize) {
        List<Role> result = transactionManger.execute(() -> roleDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, RoleDTOReq.class));
    }

    @Override
    public DTORespList<AccountDTOReq> findAllAccounts() {
        List<Account> result = transactionManger.execute(accountDAO::findAll);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

    @Override
    public DTORespList<AccountDTOReq> findAllAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findForPage(pageNumber, listSize));
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

    @Override
    public DTORespList<AccountDTOReq> findAllBlockedAccounts() {
        Supplier<List<Account>> findBlockedAccounts = () ->
                accountDAO.findAllByParameters(List.of(new ParameterContainer<>(IS_ACTIVE_ACCOUNT, false, true)));
        List<Account> result = transactionManger.execute(findBlockedAccounts);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

    @Override
    public DTORespList<AccountDTOReq> findAllBlockedAccounts(int pageNumber, int listSize) {
        Supplier<List<Account>> findBlockedAccounts = () ->
                accountDAO.findForPageByParameters(pageNumber, listSize,
                        List.of(new ParameterContainer<>(IS_ACTIVE_ACCOUNT, false, true)));

        List<Account> result = transactionManger.execute(findBlockedAccounts);
        return ExceptionManager.getListSearchResult(() -> Converter.convertListToDTO(result, AccountDTOReq.class));
    }

}
