package it.academy.exceptions.auth;

import static it.academy.utils.constants.Constants.INCORRECT_PASSWORD;

public class IncorrectPassword extends Exception{

    public IncorrectPassword() {
        super(INCORRECT_PASSWORD);
    }

}
