package it.academy.exceptions.auth;

import static it.academy.utils.Constants.FIELD_NOT_FILLED;

public class FieldNotFilledIn extends Exception{

    public FieldNotFilledIn() {
        super(FIELD_NOT_FILLED);
    }
}
