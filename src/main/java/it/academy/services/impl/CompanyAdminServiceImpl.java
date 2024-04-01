package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.PermissionDAO;
import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.ServiceAccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.PermissionDAOImpl;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dao.account.impl.ServiceAccountDAOImpl;
import it.academy.dao.service_center.ServiceCenterDAO;
import it.academy.dao.service_center.impl.ServiceCenterDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.req.service_center.ServiceCenterDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceAccount;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.services.CompanyAdminService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.services.converters.accounts.AccountConverter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.converters.accounts.RoleConverter;
import it.academy.utils.services.converters.accounts.ServiceAccountConverter;
import it.academy.utils.services.converters.service_center.ServiceCenterConverter;
import java.util.*;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class CompanyAdminServiceImpl extends UserServiceImp implements CompanyAdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);
    private ServiceAccountDAO serviceAccountDAO = new ServiceAccountDAOImpl();
    private ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();

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
        return ExceptionManager.getListSearchResult(() -> RoleConverter.convertListToDTOReq(result));
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
    public RespListDTO<AccountDTO> findAccounts() {
        List<Account> result = transactionManger.execute(accountDAO::findAll);

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findForPage(pageNumber, listSize));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Account> result = transactionManger.execute(() ->
                accountDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts() {
        List<Account> result = transactionManger.execute(() -> accountDAO.findBlockedAccounts());

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findBlockedAccountsForPage(pageNumber, listSize));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Account> result = transactionManger.execute(() ->
                accountDAO.findBlockedAccountsByParameters(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts() {
        List<ServiceAccount> result = transactionManger.execute(serviceAccountDAO::findAll);

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> ServiceAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(int pageNumber, int listSize) {
        List<ServiceAccount> result = transactionManger.execute(() -> serviceAccountDAO.findForPage(pageNumber, listSize));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> ServiceAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<ServiceAccount> result = transactionManger.execute(() ->
                serviceAccountDAO.findBlockedAccountsByParameters(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> ServiceAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<AccountDTO> addServiceAccount(AccountDTOReq req) {
        ServiceAccount account = ExceptionManager.tryExecute(() -> ServiceAccountConverter.convertAccountDTOReqToEntity(req));
        Supplier<ServiceAccount> save = () -> serviceAccountDAO.create(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectSaveResult(() -> AccountConverter.convertToDTO(transactionManger.execute(save)));
        assert account != null;
        resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, account.getEmail()));

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<ServiceCenterDTOReq> addServiceCenter(ServiceCenterDTOReq req) {
        ServiceCenter center = ExceptionManager.tryExecute(() -> ServiceCenterConverter.convertToEntity(req));
        Supplier<ServiceCenter> save = () -> serviceCenterDAO.create(center);
        RespDTO<ServiceCenterDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> ServiceCenterConverter.convertToDTOReq(transactionManger.execute(save)));
        assert center != null;
        resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, center.getServiceName()));

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<ServiceCenterDTOReq> changeServiceCenter(ServiceCenterDTOReq req) {
        ServiceCenter center = ExceptionManager.tryExecute(() -> ServiceCenterConverter.convertToEntity(req));
        Supplier<ServiceCenter> update = () -> serviceCenterDAO.update(center);
        RespDTO<ServiceCenterDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> ServiceCenterConverter.convertToDTOReq(transactionManger.execute(update)));
        assert center != null;
        resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, center.getServiceName()));

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ServiceCenterDTOReq> findServiceCenters() {
        List<ServiceCenter> result = transactionManger.execute(serviceCenterDAO::findAll);

        RespListDTO<ServiceCenterDTOReq> resp = ExceptionManager.getListSearchResult(() -> ServiceCenterConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ServiceCenterDTOReq> findServiceCenters(int pageNumber, int listSize) {
        List<ServiceCenter> result = transactionManger.execute(() -> serviceCenterDAO.findForPage(pageNumber, listSize));

        RespListDTO<ServiceCenterDTOReq> resp = ExceptionManager.getListSearchResult(() -> ServiceCenterConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ServiceCenterDTOReq> findServiceCenters(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<ServiceCenter> result = transactionManger.execute(() ->
                serviceCenterDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<ServiceCenterDTOReq> resp = ExceptionManager.getListSearchResult(() -> ServiceCenterConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

}
