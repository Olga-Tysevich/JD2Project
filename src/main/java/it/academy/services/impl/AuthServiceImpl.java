package it.academy.services.impl;

import it.academy.dao.AccountDAO;
import it.academy.dao.impl.AccountDAOImpl;
import it.academy.dto.AccountDTO;
import it.academy.dto.LoginDTO;
import it.academy.entities.Account;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.UserNotFound;
import it.academy.services.AuthService;
import it.academy.utils.converters.AccountConverter;
import it.academy.utils.dao.TransactionManger;

import static it.academy.utils.Constants.ACCOUNT_EMAIL;


public class AuthServiceImpl implements AuthService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public AccountDTO loginUser(LoginDTO loginDTO) throws UserNotFound, IncorrectPassword {

        if (!isUserExists(loginDTO.getEmail())) {
            throw new UserNotFound();
        }

        Account account = accountDAO.findByUniqueParameter(ACCOUNT_EMAIL, loginDTO.getEmail());

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
