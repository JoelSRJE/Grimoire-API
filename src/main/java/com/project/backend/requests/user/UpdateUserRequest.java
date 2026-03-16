package com.project.backend.requests.user;

public record UpdateUserRequest(
        String hashedPassword
) {
}
