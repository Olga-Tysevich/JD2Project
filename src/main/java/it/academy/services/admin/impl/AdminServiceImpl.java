package it.academy.services.admin.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.RepairWorkshopAccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.RepairWorkshopAccountDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.RepairWorkshopAccount;
import it.academy.services.admin.AdminService;
import it.academy.services.impl.UserServiceImp;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.converters.accounts.AccountConverterImpl;
import it.academy.utils.ExceptionManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.converters.accounts.RepairWorkshopAccountConverter;
import java.util.*;
import java.util.function.Supplier;
import static it.academy.utils.Constants.*;

public class AdminServiceImpl extends UserServiceImp implements AdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);
    private RepairWorkshopAccountDAO repairWorkshopAccountDAO = new RepairWorkshopAccountDAOImpl();

    @Override
    public RespDTO<AccountDTO> addRepairWorkshopAccount(AccountDTOReq req) {

        RepairWorkshopAccount account = ExceptionManager.tryExecute(() -> RepairWorkshopAccountConverter.convertDTOReqToEntity(req));
        Supplier<RepairWorkshopAccount> save = () -> repairWorkshopAccountDAO.create(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectSaveResult(() -> AccountConverterImpl.convertToDTO(transactionManger.execute(save)));

        if (account != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, account.getEmail()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<AccountDTO> changeRepairWorkshopAccount(AccountDTOReq req) {
        RepairWorkshopAccount account = ExceptionManager.tryExecute(() -> RepairWorkshopAccountConverter.convertDTOReqToEntity(req));
        Supplier<RepairWorkshopAccount> update = () -> repairWorkshopAccountDAO.update(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectUpdateResult(() -> AccountConverterImpl.convertToDTO(transactionManger.execute(update)));

        if (account != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, account.getEmail()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<AccountDTO> addAdminAccount(AccountDTOReq req) {
        return getAccountDTORespDTO(req, accountDAO, transactionManger);
    }

    public static RespDTO<AccountDTO> getAccountDTORespDTO(AccountDTOReq req, AccountDAO<Account> accountDAO, TransactionManger transactionManger) {
        Account account = ExceptionManager.tryExecute(() -> AccountConverterImpl.convertDTOReqToEntity(req));
        Supplier<Account> save = () -> accountDAO.create(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectSaveResult(() -> AccountConverterImpl.convertToDTO(transactionManger.execute(save)));

        if (account != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, account.getEmail()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<AccountDTO> changeAdminAccount(AccountDTOReq req) {
        Account account = ExceptionManager.tryExecute(() -> AccountConverterImpl.convertDTOReqToEntity(req));
        Supplier<Account> update = () -> accountDAO.update(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectUpdateResult(() -> AccountConverterImpl.convertToDTO(transactionManger.execute(update)));

        if (account != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, account.getEmail()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts() {
        List<Account> result = transactionManger.execute(accountDAO::findAll);

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverterImpl.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findForPage(pageNumber, listSize));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverterImpl.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Account> result = transactionManger.execute(() ->
                accountDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverterImpl.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts() {
        List<Account> result = transactionManger.execute(() -> accountDAO.findBlockedAccounts());

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverterImpl.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts(int pageNumber, int listSize) {
        List<Account> result = transactionManger.execute(() -> accountDAO.findBlockedAccountsForPage(pageNumber, listSize));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverterImpl.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findBlockedAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Account> result = transactionManger.execute(() ->
                accountDAO.findBlockedAccountsByParameters(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> AccountConverterImpl.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts() {
        List<RepairWorkshopAccount> result = transactionManger.execute(repairWorkshopAccountDAO::findAll);

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> RepairWorkshopAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(int pageNumber, int listSize) {
        List<RepairWorkshopAccount> result = transactionManger.execute(() -> repairWorkshopAccountDAO.findForPage(pageNumber, listSize));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> RepairWorkshopAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<AccountDTO> findServiceAccounts(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<RepairWorkshopAccount> result = transactionManger.execute(() ->
                repairWorkshopAccountDAO.findBlockedAccountsByParameters(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<AccountDTO> resp = ExceptionManager.getListSearchResult(() -> RepairWorkshopAccountConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

}
