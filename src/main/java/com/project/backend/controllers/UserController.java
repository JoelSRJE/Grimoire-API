package com.project.backend.controllers;

import com.project.backend.dtos.user.FoundUserDto;
import com.project.backend.dtos.user.LoginUserDto;
import com.project.backend.dtos.user.RegisteredUserDto;
import com.project.backend.requests.user.LoginUserRequest;
import com.project.backend.requests.user.LogoutUserRequest;
import com.project.backend.requests.user.RegisterUserRequest;
import com.project.backend.models.user.User;
import com.project.backend.requests.user.UpdateUserRequest;
import com.project.backend.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<RegisteredUserDto> registerUser(
            @RequestBody RegisterUserRequest request
    ) {
        User registeredUser = userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(RegisteredUserDto.from(registeredUser));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FoundUserDto> getSpecificUser(
            @PathVariable UUID userId
            ) {
        User foundUser = userService.getSpecificUser(userId);

        return ResponseEntity.ok(FoundUserDto.from(foundUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable UUID userId
    ) {
        userService.deleteUser(userId);

        return ResponseEntity.ok().body("User with id: " + userId + " was deleted!");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable UUID userId,
            @RequestBody UpdateUserRequest request
            ) {
        userService.updateUser(userId, request);

        return ResponseEntity.ok().body("Successfully updated password!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserDto> loginUser(
            @RequestBody LoginUserRequest request
            ) {
        User loginUser = userService.loginUser(request);

        return ResponseEntity.ok(LoginUserDto.from(loginUser));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @AuthenticationPrincipal User authenticatedUser
            ) {
        String message = userService.logoutUser(authenticatedUser.getEmail());

        System.out.println("User: " + authenticatedUser);

        return ResponseEntity.ok().body(message);
    }
}
