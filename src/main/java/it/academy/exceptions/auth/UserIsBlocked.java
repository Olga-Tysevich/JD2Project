package it.academy.exceptions.auth;

import static it.academy.utils.Constants.USER_IS_BLOCKED;

public class UserIsBlocked extends Exception {

    public UserIsBlocked() {
        super(USER_IS_BLOCKED);
    }
}
