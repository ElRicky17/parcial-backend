package com.sinnoquierohacernada.parcialyfinal.controllers;


import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginRequest;
import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginResponse;
import com.sinnoquierohacernada.parcialyfinal.enums.Role;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.services.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
                Role.DAEMON
        );

    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody String role) {
        return authService.updateUser(id, role);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID id) {
        try {
            authService.deleteUser(id);
            return " Usuario eliminado correctamente";
        } catch (RuntimeException e) {
            return " Error: " + e.getMessage();
        } catch (Exception e) {
            return " Error interno del servidor: " + e.getMessage();
        }
    }
}