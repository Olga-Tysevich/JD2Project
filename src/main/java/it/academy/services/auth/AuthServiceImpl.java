package it.academy.services.auth;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.AccountDAOImpl;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.login.req.LoginDTO;
import it.academy.entities.account.Account;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.UserIsBlocked;
import it.academy.exceptions.auth.UserNotFound;
import it.academy.utils.converters.account.AccountConverter;
import it.academy.utils.dao.TransactionManger;


import static it.academy.utils.Constants.ACCOUNT_EMAIL;


public class AuthServiceImpl implements AuthService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public AccountDTO loginUser(LoginDTO loginDTO) throws UserNotFound, IncorrectPassword, UserIsBlocked {

        if (!isUserExists(loginDTO.getEmail())) {
            throw new UserNotFound();
        }

        Account account = accountDAO.findByUniqueParameter(ACCOUNT_EMAIL, loginDTO.getEmail());

        if (account.getIsActive()) {
            throw new UserIsBlocked();
        }

        if (!account.getPassword().equals(loginDTO.getPassword())) {
            throw new IncorrectPassword();
        }

        return AccountConverter.convertToDTO(account);
    }


    private boolean isUserExists(String email) {
        return transactionManger.execute(() ->
                accountDAO.findByUniqueParameter(ACCOUNT_EMAIL, email)) != null;
    }

}
