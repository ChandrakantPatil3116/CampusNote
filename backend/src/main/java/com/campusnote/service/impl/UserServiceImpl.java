package com.campusnote.service.impl;

import com.campusnote.dto.LoginRequest;
import com.campusnote.dto.RegisterRequest;
import com.campusnote.dto.UserResponse;
import com.campusnote.entity.Role;
import com.campusnote.entity.User;
import com.campusnote.repository.UserRepository;
import com.campusnote.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.campusnote.dto.LoginResponse;
import com.campusnote.security.JwtService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse getUserById(Integer userId) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();

        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() ->
                new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        UserResponse response = new UserResponse();

        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token, response);
    }

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.STUDENT);

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setUserId(savedUser.getUserId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;
    }

}