package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.ServiceAccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.ServiceAccountDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.RepairWorkshopAccount;
import it.academy.services.CompanyAdminService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.services.converters.accounts.AccountConverter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.converters.accounts.ServiceAccountConverter;
import java.util.*;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class CompanyAdminServiceImpl extends UserServiceImp implements CompanyAdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);
    private ServiceAccountDAO serviceAccountDAO = new ServiceAccountDAOImpl();


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
        List<RepairWorkshopAccount> result = transactionManger.execute(serviceAccountDAO::findAll);

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> ServiceAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(int pageNumber, int listSize) {
        List<RepairWorkshopAccount> result = transactionManger.execute(() -> serviceAccountDAO.findForPage(pageNumber, listSize));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> ServiceAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<RepairWorkshopAccount> result = transactionManger.execute(() ->
                serviceAccountDAO.findBlockedAccountsByParameters(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> ServiceAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<AccountDTO> addServiceAccount(AccountDTOReq req) {
        RepairWorkshopAccount account = ExceptionManager.tryExecute(() -> ServiceAccountConverter.convertAccountDTOReqToEntity(req));
        Supplier<RepairWorkshopAccount> save = () -> serviceAccountDAO.create(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectSaveResult(() -> AccountConverter.convertToDTO(transactionManger.execute(save)));

        if (account != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, account.getEmail()));
        }

        transactionManger.closeManager();
        return resp;
    }

}
