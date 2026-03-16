package com.project.backend.services.user;

import com.project.backend.exceptions.user.*;
import com.project.backend.requests.user.LoginUserRequest;
import com.project.backend.requests.user.RegisterUserRequest;
import com.project.backend.models.user.User;
import com.project.backend.repositories.UserRepository;
import com.project.backend.requests.user.UpdateUserRequest;
import com.project.backend.utils.JWTService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public User registerUser(RegisterUserRequest request) {
        if (request.email() == null || request.email().isBlank()) {
            throw new EmailIsEmptyException();
        }

        if (request.password() == null || request.password().isBlank()) {
            throw new PasswordIsEmptyException();
        }

        String hashedPassword = passwordEncoder.encode(request.password());

        User user = new User(request.email(), hashedPassword);

        return  userRepository.save(user);
    }

    @Override
    public User getSpecificUser(UUID userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(UserDoesntExistException::new);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User foundUser = getSpecificUser(userId);

        userRepository.delete(foundUser);
    }

    @Override
    @Transactional
    public void updateUser(UUID userId, UpdateUserRequest request) {
        User updateUser = getSpecificUser(userId);

        if (request.hashedPassword() != null && !request.hashedPassword().isBlank()) {
            String newHashedPassword = passwordEncoder.encode(request.hashedPassword());

            updateUser.setHashedPassword(newHashedPassword);
        }

        userRepository.save(updateUser);
    }

    @Override
    public User loginUser(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(UserDoesntExistException::new);

        boolean correctPassword = passwordEncoder.matches(request.password(), user.getHashedPassword());

        if (!correctPassword) {
            throw new IncorrectPasswordException();
        }

        updateUserLoginStatus(user.getUserId(), true);

        String token = jwtService.generateToken(user.getUserId());
        user.setAuthToken(token);

        return user;
    }

    @Override
    public String logoutUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserDoesntExistException::new);

        updateUserLoginStatus(user.getUserId(), false);

        String message = "User successfully logged out!";

        return message;
    }

    private void updateUserLoginStatus(UUID userId, boolean isOnline) {
        User user = getSpecificUser(userId);

        user.setOnline(isOnline);

        userRepository.save(user);
    }
}
