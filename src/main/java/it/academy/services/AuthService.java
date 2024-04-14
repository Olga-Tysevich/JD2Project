package it.academy.services;

import it.academy.dto.req.LoginDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.UserIsBlocked;
import it.academy.exceptions.auth.UserNotFound;

public interface AuthService {

    AccountDTO loginUser(LoginDTO loginDTO) throws UserNotFound, IncorrectPassword, UserIsBlocked;

}
