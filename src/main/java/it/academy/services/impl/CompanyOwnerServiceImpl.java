package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.DTOResp;
import it.academy.entities.account.Account;
import it.academy.services.CompanyOwnerService;
import it.academy.utils.Converter;
import it.academy.utils.ExceptionManager;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.TransactionManger;

import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class CompanyOwnerServiceImpl extends CompanyAdminServiceImpl implements CompanyOwnerService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public DTOResp addAdminAccount(AccountDTOReq req) {
        req.setId(0L);
        Account account = Converter.convertToEntity(req);
        Supplier<Account> save = () -> accountDAO.create(account);
        try {
            transactionManger.execute(save);
        } catch (Exception e) {
            return ExceptionManager.getResp(e);
        }
        return DTOResp.builder()
                .httpStatus(SC_CREATED)
                .message(String.format(MessageManager.getProperty(SAVED_SUCCESSFULLY), account.getEmail()))
                .build();
    }

    @Override
    public DTOResp changeAdminAccount(AccountDTOReq req) {
        Account account = Converter.convertToEntity(req);
        Supplier<Account> update = () -> accountDAO.update(account);
        try {
            transactionManger.execute(update);
        } catch (Exception e) {
            return ExceptionManager.getResp(e);
        }
        return DTOResp.builder()
                .httpStatus(SC_OK)
                .message(String.format(MessageManager.getProperty(UPDATED_SUCCESSFULLY), account.getEmail()))
                .build();
    }

}
