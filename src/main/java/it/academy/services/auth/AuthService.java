package it.academy.services.auth;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.login.req.LoginDTO;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.UserNotFound;

public interface AuthService {

    AccountDTO loginUser(LoginDTO loginDTO) throws UserNotFound, IncorrectPassword;

}
