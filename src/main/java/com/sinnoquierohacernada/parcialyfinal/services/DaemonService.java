package com.sinnoquierohacernada.parcialyfinal.services;

import com.sinnoquierohacernada.parcialyfinal.dao.UserRepository;
import com.sinnoquierohacernada.parcialyfinal.enums.Role;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DaemonService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public DaemonService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public String assignRewardOrPunishment(UUID daemonId, String action) {
        // lógica simplificada
        Optional<User> daemon = userRepository.findById(daemonId);
        if (daemon.isEmpty() || daemon.get().getRole() != Role.DAEMON) {
            return "El usuario no es un Daemon válido";
        }
        return "Daemon " + daemonId + " recibió: " + action;
    }

}
