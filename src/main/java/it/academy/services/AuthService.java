package it.academy.services;

import it.academy.dto.AccountDTO;
import it.academy.dto.LoginDTO;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.UserNotFound;

public interface AuthService {

    AccountDTO loginUser(LoginDTO loginDTO) throws UserNotFound, IncorrectPassword;

}
