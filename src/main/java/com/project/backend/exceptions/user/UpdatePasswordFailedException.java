package com.project.backend.exceptions.user;

public class UpdatePasswordFailedException extends RuntimeException {
    public UpdatePasswordFailedException() {
        super("Something went wrong when updating the password!");
    }
}
