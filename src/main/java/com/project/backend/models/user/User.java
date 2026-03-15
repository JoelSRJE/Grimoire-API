package com.project.backend.models.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public User() {}

    public User(String email, String password) {
        this.userId = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.accountType = AccountType.USER;
        this.createdAt = LocalDateTime.now();
    }
}
