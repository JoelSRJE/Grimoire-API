package com.project.backend.exceptions.user;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Password is not correct!");
    }
}
