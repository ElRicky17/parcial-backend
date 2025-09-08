package com.sinnoquierohacernada.parcialyfinal.services;

import com.sinnoquierohacernada.parcialyfinal.dao.UserRepository;
import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginRequest;
import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginResponse;
import com.sinnoquierohacernada.parcialyfinal.enums.Role;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return response;
    }

    public User registerUser(String email, String password, Role role) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public String assignRewardOrPunishment(Long daemonId, String action) {
        // lógica simplificada
        Optional<User> daemon = userRepository.findById(daemonId);
        if (daemon.isEmpty() || daemon.get().getRole() != Role.DAEMON) {
            return "El usuario no es un Daemon válido";
        }
        return "Daemon " + daemonId + " recibió: " + action;
    }

    public Map<String, Object> getDaemonStats(Long daemonId) {
        // lógica simplificada
        Map<String, Object> stats = new HashMap<>();
        stats.put("daemonId", daemonId);
        stats.put("missionsCompleted", 5);
        stats.put("reportsSubmitted", 3);
        return stats;
    }
}
