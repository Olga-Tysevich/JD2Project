package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.entities.account.Account;
import it.academy.services.CompanyOwnerService;
import it.academy.utils.MessageManager;
import it.academy.utils.services.converters.accounts.AccountConverter;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.dao.TransactionManger;

import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class CompanyOwnerServiceImpl extends CompanyAdminServiceImpl implements CompanyOwnerService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public RespDTO<AccountDTO> addAdminAccount(AccountDTOReq req) {
        Account account = ExceptionManager.tryExecute(() -> AccountConverter.convertAccountDTOReqToEntity(req));
        Supplier<Account> save = () -> accountDAO.create(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectSaveResult(() -> AccountConverter.convertToDTO(transactionManger.execute(save)));

        if (account != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, account.getEmail()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<AccountDTO> changeAdminAccount(AccountDTOReq req) {
        Account account = ExceptionManager.tryExecute(() -> AccountConverter.convertAccountDTOReqToEntity(req));
        Supplier<Account> update = () -> accountDAO.update(account);
        RespDTO<AccountDTO> resp = ExceptionManager.getObjectUpdateResult(() -> AccountConverter.convertToDTO(transactionManger.execute(update)));

        if (account != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, account.getEmail()));
        }

        transactionManger.closeManager();
        return resp;
    }

}
