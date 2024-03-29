package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.entities.account.Account;
import it.academy.services.CompanyOwnerService;
import it.academy.utils.MessageManager;
import it.academy.utils.services.Converter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.TransactionManger;

import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class CompanyOwnerServiceImpl extends CompanyAdminServiceImpl implements CompanyOwnerService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public RespDTO addAdminAccount(AccountDTOReq req) {
        req.setId(0L);
        Account account = ExceptionManager.getObjectConvertResult(() -> Converter.tryConvertToEntity(req, Account.class));
        Supplier<Account> save = () -> accountDAO.create(account);
        RespDTO resp = ExceptionManager.getObjectSaveResult(() -> transactionManger.execute(save));
        assert account != null;
        resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, account.getEmail()));

        return resp;
    }

    @Override
    public RespDTO changeAdminAccount(AccountDTOReq req) {
        Account account = ExceptionManager.getObjectConvertResult(() -> Converter.tryConvertToEntity(req, Account.class));
        Supplier<Account> update = () -> accountDAO.update(account);
        RespDTO resp = ExceptionManager.getObjectUpdateResult(() -> transactionManger.execute(update));
        assert account != null;
        resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, account.getEmail()));

        return resp;
    }

}
