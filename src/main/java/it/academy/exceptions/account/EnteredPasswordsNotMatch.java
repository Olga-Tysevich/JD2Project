package it.academy.exceptions.account;

import static it.academy.utils.constants.Constants.PASSWORDS_NOT_MATCH;

public class EnteredPasswordsNotMatch extends Exception {

    public EnteredPasswordsNotMatch() {
        super(PASSWORDS_NOT_MATCH);
    }

}
