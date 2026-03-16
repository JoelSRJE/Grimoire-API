package com.project.backend.dtos.user;

import com.project.backend.models.user.AccountType;
import com.project.backend.models.user.User;

import java.time.LocalDateTime;

public record FoundUserDto(
        String email,
        AccountType accountType,
        LocalDateTime createdAt
) {
    public static FoundUserDto from(User user) {
        return new FoundUserDto(
                user.getEmail(),
                user.getAccountType(),
                user.getCreatedAt()
        );
    }
}
