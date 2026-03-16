package com.project.backend.requests.user;

public record RegisterUserRequest(
        String email,
        String password
) {
}
