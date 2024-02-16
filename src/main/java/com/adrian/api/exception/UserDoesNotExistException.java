package com.adrian.api.exception;

public class UserDoesNotExistException extends Exception {

    private static final String message = "User doesn't exist";

    public UserDoesNotExistException() {
        super(message);
    }
}
