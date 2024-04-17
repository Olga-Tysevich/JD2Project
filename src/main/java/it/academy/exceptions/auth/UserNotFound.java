package it.academy.exceptions.auth;

import static it.academy.utils.constants.Constants.USER_NOT_FOUND;

public class UserNotFound extends Exception {

    public UserNotFound() {
        super(USER_NOT_FOUND);
    }
}
