package com.project.backend.dtos.user;

import com.project.backend.models.user.AccountType;
import com.project.backend.models.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoginUserDto(
        UUID userId,
        String email,
        AccountType accountType,
        LocalDateTime createdAt,
        boolean isOnline,
        String authToken
) {
    public static LoginUserDto from(User user) {
        return new LoginUserDto(
                user.getUserId(),
                user.getEmail(),
                user.getAccountType(),
                user.getCreatedAt(),
                user.isOnline(),
                user.getAuthToken()
        );
    }
}
