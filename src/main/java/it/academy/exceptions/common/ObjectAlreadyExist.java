package it.academy.exceptions.common;

public class ObjectAlreadyExist extends RuntimeException {

    public ObjectAlreadyExist(String message) {
        super(message);
    }

    public ObjectAlreadyExist() {
    }
}
