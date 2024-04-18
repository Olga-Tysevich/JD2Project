package it.academy.exceptions.auth;

import static it.academy.utils.constants.MessageConstants.INVALID_ROLE;

public class InvalidRole extends RuntimeException {
    public InvalidRole() {
        super(INVALID_ROLE);
    }
}
