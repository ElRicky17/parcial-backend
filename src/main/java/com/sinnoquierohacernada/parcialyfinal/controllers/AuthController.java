package com.sinnoquierohacernada.parcialyfinal.controllers;


import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginRequest;
import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginResponse;
import com.sinnoquierohacernada.parcialyfinal.enums.Role;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.services.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public User register(@RequestBody LoginRequest request) {
        return authService.registerUser(
                request.getEmail(),
                request.getPassword(),
                Role.DAEMON // aquí decides el rol por defecto o según el request
        );
    }
}