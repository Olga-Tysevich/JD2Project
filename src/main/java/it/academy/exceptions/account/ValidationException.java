package it.academy.exceptions.account;


public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}
