package com.campusnote.service;

import com.campusnote.dto.LoginRequest;
import com.campusnote.dto.RegisterRequest;
import com.campusnote.dto.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);

    UserResponse login(LoginRequest request);

    UserResponse getUserById(Integer userId);
}