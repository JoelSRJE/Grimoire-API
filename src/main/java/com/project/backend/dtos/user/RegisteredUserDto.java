package com.project.backend.dtos.user;

import com.project.backend.models.user.AccountType;
import com.project.backend.models.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisteredUserDto(
        UUID userId,
        String email,
        AccountType accountType,
        LocalDateTime createdAt
) {
    public static RegisteredUserDto from(User user) {
        return new RegisteredUserDto(
                user.getUserId(),
                user.getEmail(),
                user.getAccountType(),
                user.getCreatedAt()
        );
    }
}
