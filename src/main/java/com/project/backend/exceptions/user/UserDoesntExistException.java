package com.project.backend.exceptions.user;

public class UserDoesntExistException extends RuntimeException {
    public UserDoesntExistException() {
        super("User doesn't exist in database!");
    }
}
