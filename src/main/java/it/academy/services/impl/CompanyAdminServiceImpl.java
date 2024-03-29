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
import it.academy.utils.Converter;
import it.academy.utils.ExceptionManager;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;
import static javax.servlet.http.HttpServletResponse.*;

public class CompanyAdminServiceImpl implements CompanyAdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public DTOResp createRole(RoleDTOReq req) {

        Set<Permission> permissionSet = req.getPermissions();
        permissionSet.forEach(p -> permissionDAO.findAllByParameters(true,
                List.of(new ParameterContainer<>(PERMISSION_TYPE, p.getType().name()),
                        new ParameterContainer<>(PERMISSION_CATEGORY, p.getCategory().name())))
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

        try {
            transactionManger.execute(find);
        } catch (Exception e) {
            DTOResp resp = ExceptionManager.getResp(e);
            resp.setHttpStatus(SC_INTERNAL_SERVER_ERROR);
            return resp;
        }

        return DTOResp.builder()
                .httpStatus(SC_CREATED)
                .message(String.format(MessageManager.getProperty(SAVED_SUCCESSFULLY), role.getName()))
                .build();
    }

    @Override
    public DTORespList<Role> findAllRoles() {
        List<Role> result = transactionManger.execute(roleDAO::findAll);
        return getDTORespList(result);
    }

    @Override
    public DTORespList<Role> findAllRolesForPage(int pageNumber, int listSize) {
        List<Role> result = transactionManger.execute(() -> roleDAO.findForPage(pageNumber, listSize));
        return getDTORespList(result);
    }

    @Override
    public DTORespList<AccountDTOReq> findAllAccounts() {
        List<Account> result = transactionManger.execute(accountDAO::findAll);
        return getDTORespList(result);
    }

    @Override
    public DTORespList<AccountDTOReq> findAllAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findForPage(pageNumber, listSize));
        return getDTORespList(result);
    }

    @Override
    public DTORespList<AccountDTOReq> findAllBlockedAccounts() {
        Supplier<List<Account>> findBlockedAccounts = () ->
                accountDAO.findAllByParameters(true, List.of(new ParameterContainer<>(IS_ACTIVE_ACCOUNT, false)));
        List<Account> result = transactionManger.execute(findBlockedAccounts);
        return getDTORespList(result);
    }

    @Override
    public DTORespList<AccountDTOReq> findAllBlockedAccounts(int pageNumber, int listSize) {
        Supplier<List<Account>> findBlockedAccounts = () ->
                accountDAO.findForPageByParameters(true, pageNumber, listSize,
                        List.of(new ParameterContainer<>(IS_ACTIVE_ACCOUNT, false)));
        List<Account> result = transactionManger.execute(findBlockedAccounts);
        return getDTORespList(result);
    }

    private <T, R> DTORespList<T> getDTORespList(List<R> resultList) {
        try {
            List<T> result = new ArrayList<>();
            for (R t : resultList) {
                T convertToDTO = Converter.convertToDTO(t);
                result.add(convertToDTO);
            }
            return DTORespList.<T>builder()
                    .httpStatus(SC_OK)
                    .message(String.format(MessageManager.getProperty(OBJECT_FOUND), result.size()))
                    .list(result)
                    .build();
        } catch (Exception e) {
            DTORespList<T> resp = ExceptionManager.getRespList(e);
            resp.setHttpStatus(SC_INTERNAL_SERVER_ERROR);
            return resp;
        }
    }

}
