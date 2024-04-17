package it.academy.exceptions.common;

import static it.academy.utils.constants.Constants.ACCESS_IS_DENIED;

public class AccessDenied extends RuntimeException{

    public AccessDenied() {
        super(ACCESS_IS_DENIED);
    }

}
