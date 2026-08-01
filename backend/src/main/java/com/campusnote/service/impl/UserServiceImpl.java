package com.campusnote.service.impl;

import com.campusnote.dto.LoginRequest;
import com.campusnote.dto.RegisterRequest;
import com.campusnote.dto.UserResponse;
import com.campusnote.repository.UserRepository;
import com.campusnote.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getUserById(Integer userId) {
        return null;
    }

    @Override
    public UserResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        return null;
    }

}