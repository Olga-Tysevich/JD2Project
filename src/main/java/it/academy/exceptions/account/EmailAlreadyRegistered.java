package it.academy.exceptions.account;

import static it.academy.utils.Constants.EMAIL_ALREADY_EXISTS;

public class EmailAlreadyRegistered extends Exception {

    public EmailAlreadyRegistered(String email) {
        super(String.format(EMAIL_ALREADY_EXISTS, email));
    }
}
