package com.sinnoquierohacernada.parcialyfinal.controllers;

import com.sinnoquierohacernada.parcialyfinal.models.Report;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.services.AuthService;
import com.sinnoquierohacernada.parcialyfinal.services.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/andrei")
@PreAuthorize("hasAuthority('ANDREI')")
public class AndreiController {

    private final AuthService authService;
    private final ReportService reportService;

    public AndreiController(AuthService authService, ReportService reportService) {
        this.authService = authService;
        this.reportService = reportService;
    }

    // Ver todos los usuarios
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    // Ver todos los reportes
    @GetMapping("/reports")
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    // Asignar castigo o recompensa a un Daemon
    @PostMapping("/assign/{daemonId}")
    public String assignRewardOrPunishment(@PathVariable Long daemonId,
                                           @RequestParam String action) {
        return authService.assignRewardOrPunishment(daemonId, action);
    }
}
