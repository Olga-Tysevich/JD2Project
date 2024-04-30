package it.academy.exceptions.account;

import static it.academy.utils.constants.Constants.EMAIL_ALREADY_EXISTS;

public class EmailAlreadyRegistered extends RuntimeException {

    public EmailAlreadyRegistered(String email) {
        super(String.format(EMAIL_ALREADY_EXISTS, email));
    }
}
