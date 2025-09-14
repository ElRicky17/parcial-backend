package com.sinnoquierohacernada.parcialyfinal.services;

import com.sinnoquierohacernada.parcialyfinal.dao.UserRepository;
import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginRequest;
import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginResponse;
import com.sinnoquierohacernada.parcialyfinal.enums.Role;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setUserId(user.getId());
        return response;
    }

    public User registerUser(String email, String password, Role role) {
        if (userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);

            return userRepository.save(user);
        } else {
            throw new RuntimeException("El usuario ya existe");
        }

    }
    public User updateUser(UUID userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (roleName != null && !roleName.isBlank()) {
            try {
                Role newRole = Role.valueOf(roleName.trim().toUpperCase());
                user.setRole(newRole);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Rol inválido: " + roleName);
            }
        }

        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(userId);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
