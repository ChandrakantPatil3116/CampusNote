package com.campusnote.controller;
import com.campusnote.dto.LoginRequest;
import com.campusnote.dto.RegisterRequest;
import com.campusnote.dto.UserResponse;
import com.campusnote.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody RegisterRequest request) {

        UserResponse response = userService.register(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @RequestBody LoginRequest request) {

        UserResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }
}