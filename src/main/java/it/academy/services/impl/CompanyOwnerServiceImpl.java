package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.DTOResp;
import it.academy.entities.account.Account;
import it.academy.services.CompanyOwnerService;
import it.academy.utils.services.Converter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.TransactionManger;
import java.util.function.Supplier;

public class CompanyOwnerServiceImpl extends CompanyAdminServiceImpl implements CompanyOwnerService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public DTOResp addAdminAccount(AccountDTOReq req) {
        req.setId(0L);
        Account account = ExceptionManager.getObjectConvertResult(() -> Converter.tryConvertToEntity(req, Account.class));
        Supplier<Account> save = () -> accountDAO.create(account);

        return ExceptionManager.getObjectSaveResult(() -> transactionManger.execute(save));
    }

    @Override
    public DTOResp changeAdminAccount(AccountDTOReq req) {
        Account account = ExceptionManager.getObjectConvertResult(() -> Converter.tryConvertToEntity(req, Account.class));
        Supplier<Account> update = () -> accountDAO.update(account);

        return ExceptionManager.getObjectUpdateResult(() ->  transactionManger.execute(update));
    }

}
