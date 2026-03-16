package com.project.backend.services.user;

import com.project.backend.requests.user.LoginUserRequest;
import com.project.backend.requests.user.LogoutUserRequest;
import com.project.backend.requests.user.RegisterUserRequest;
import com.project.backend.models.user.User;
import com.project.backend.requests.user.UpdateUserRequest;

import java.util.UUID;

public interface IUserService {
    /* Standard CRUD operations for the endpoint */
    User registerUser(RegisterUserRequest request);
    User getSpecificUser(UUID userId);
    void deleteUser(UUID userId);
    void updateUser(UUID userId, UpdateUserRequest request);

    /* Login methods */
    User loginUser(LoginUserRequest request);
    String logoutUser(String email);
}
