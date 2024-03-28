package it.academy.services.impl;

import it.academy.dao.account.PermissionDAO;
import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.PermissionDAOImpl;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.DTOResp;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.services.CompanyAdminService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class CompanyAdminServiceImpl implements CompanyAdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();

    @Override
    public DTOResp createRole(RoleDTOReq req) {

        Set<Permission> permissionSet = req.getPermissions();
        permissionSet.forEach(p -> permissionDAO.findAllByParameters(
                List.of(new ParameterContainer(PERMISSION_TYPE, String.valueOf(p.getType().ordinal())),
                        new ParameterContainer(PERMISSION_CATEGORY, String.valueOf(p.getType().ordinal()))))
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
        transactionManger.execute(find);

        return DTOResp.builder()
                .httpStatus(SC_OK)
                .message(MessageManager.getProperty(SAVED_SUCCESSFULLY))
                .build();
    }

}
