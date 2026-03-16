package com.project.backend.exceptions.user;

public class EmailIsEmptyException extends RuntimeException {
    public EmailIsEmptyException() {
        super("Email cannot be empty or null!");
    }
}
