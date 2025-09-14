package com.sinnoquierohacernada.parcialyfinal.controllers;

import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginRequest;
import com.sinnoquierohacernada.parcialyfinal.enums.Role;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.services.DaemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/daemon")
@RequiredArgsConstructor
public class DaemonController {

    private final DaemonService daemonService;



    @PostMapping("/register")
    public User registerDaemon(@RequestBody LoginRequest request) {

        return daemonService.registerUser(
                request.getEmail(),
                request.getPassword(),
                Role.NETWORK_ADMIN
        );
    }

    @PostMapping("/assign/{daemonId}")
    public String assignRewardOrPunishment(@PathVariable UUID daemonId,
                                           @RequestParam String action) {
        return daemonService.assignRewardOrPunishment(daemonId, action);
    }
}
