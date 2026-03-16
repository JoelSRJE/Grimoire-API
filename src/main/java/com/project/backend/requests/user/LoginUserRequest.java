package com.project.backend.requests.user;

public record LoginUserRequest(
        String email,
        String password
) {
}
