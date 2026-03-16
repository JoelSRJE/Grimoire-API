package com.project.backend.exceptions.user;

public class UserDidntRegisterException extends RuntimeException {
    public UserDidntRegisterException() {
        super("The user did not register correctly in database!");
    }
}
