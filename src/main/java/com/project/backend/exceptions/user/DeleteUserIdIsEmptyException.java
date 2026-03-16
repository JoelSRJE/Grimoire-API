package com.project.backend.exceptions.user;

public class DeleteUserIdIsEmptyException extends RuntimeException {
    public DeleteUserIdIsEmptyException() {
        super("UserId cannot be empty or null!");
    }
}
