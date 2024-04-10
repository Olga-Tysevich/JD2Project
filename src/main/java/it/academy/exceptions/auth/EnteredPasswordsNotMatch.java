package it.academy.exceptions.auth;

import static it.academy.utils.Constants.PASSWORDS_NOT_MATCH;

public class EnteredPasswordsNotMatch extends Exception {

    public EnteredPasswordsNotMatch() {
        super(PASSWORDS_NOT_MATCH);
    }

}
