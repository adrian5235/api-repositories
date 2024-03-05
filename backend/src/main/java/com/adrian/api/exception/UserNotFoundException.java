package com.adrian.api.exception;

public class UserNotFoundException extends Exception {

    private static final String message = "The given user couldn't be found";

    public UserNotFoundException() {
        super(message);
    }
}
