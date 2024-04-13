package it.academy.exceptions.common;

import static it.academy.utils.Constants.ACCESS_IS_DENIED;

public class AccessDenied extends Exception{

    public AccessDenied() {
        super(ACCESS_IS_DENIED);
    }

}
