package it.academy.services.admin.impl;

import it.academy.dao.account.PermissionDAO;
import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.PermissionDAOImpl;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.services.admin.RoleService;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.ExceptionManager;
import it.academy.utils.converters.accounts.PermissionConverter;
import it.academy.utils.converters.accounts.RoleConverter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class RoleServiceImpl implements RoleService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();

    @Override
    public RespDTO<RoleDTOReq> createRole(RoleDTOReq req) {

        Set<Permission> permissionSet = new HashSet<>();
        req.getPermissions().forEach(p -> {
                    Permission permission = permissionDAO.findByCategoryAndType(
                            List.of(new ParameterContainer<>(PERMISSION_TYPE, p.getType()),
                                    new ParameterContainer<>(PERMISSION_CATEGORY, p.getCategory())));
                    if (permission == null) {
                        ExceptionManager.tryExecute(() -> transactionManger.execute(() -> permissionDAO.create(p)));
                    }
                    permissionSet.add(permission);
                }
        );

        Role role = Role.builder()
                .name(req.getName())
                .permissions(permissionSet)
                .build();

        Supplier<Role> find = () -> {
            Role result = roleDAO.findByUniqueParameter(ROLE_NAME, req.getName());
            if (result == null) {
                result = roleDAO.create(role);
            }
            return result;
        };

        RespDTO<RoleDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> RoleConverter.convertToDTOReq(transactionManger.execute(find)));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RoleDTOReq> findRoles() {
        List<Role> result = transactionManger.execute(roleDAO::findAll);
        RespListDTO<RoleDTOReq> resp = ExceptionManager.getListSearchResult(() -> RoleConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RoleDTOReq> findRoles(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Role> result = transactionManger.execute(() ->
                roleDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<RoleDTOReq> resp = ExceptionManager.getListSearchResult(() -> RoleConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RoleDTOReq> findRoles(int pageNumber, int listSize) {
        List<Role> result = transactionManger.execute(() -> roleDAO.findForPage(pageNumber, listSize));

        RespListDTO<RoleDTOReq> resp = ExceptionManager.getListSearchResult(() -> RoleConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }


    @Override
    public RespListDTO<PermissionDTOReq> findPermissions() {
        List<Permission> result = transactionManger.execute(() -> permissionDAO.findAll());
        RespListDTO<PermissionDTOReq> resp = ExceptionManager.getListSearchResult(() -> PermissionConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<PermissionDTOReq> findPermissions(int pageNumber, int listSize) {
        List<Permission> result = transactionManger.execute(() -> permissionDAO.findForPage(pageNumber, listSize));
        RespListDTO<PermissionDTOReq> resp = ExceptionManager.getListSearchResult(() -> PermissionConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<PermissionDTOReq> findPermissions(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Permission> result = transactionManger.execute(() ->
                permissionDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<PermissionDTOReq> resp = ExceptionManager.getListSearchResult(() -> PermissionConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
