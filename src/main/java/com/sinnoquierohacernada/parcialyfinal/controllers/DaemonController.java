package com.sinnoquierohacernada.parcialyfinal.controllers;

import com.sinnoquierohacernada.parcialyfinal.dto.Auth.LoginRequest;
import com.sinnoquierohacernada.parcialyfinal.enums.Role;
import com.sinnoquierohacernada.parcialyfinal.models.Report;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.services.AuthService;
import com.sinnoquierohacernada.parcialyfinal.services.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/daemons")
@PreAuthorize("hasAuthority('DAEMON')")
public class DaemonController {

    private final AuthService authService;
    private final ReportService reportService;

    public DaemonController(AuthService authService, ReportService reportService) {
        this.authService = authService;
        this.reportService = reportService;
    }

    @PostMapping("/register-victim")
    public User registerVictim(@RequestBody LoginRequest request) {
        return authService.registerUser(
                request.getEmail(),
                request.getPassword(),
                Role.NETWORK_ADMIN
        );
    }

    @GetMapping("/stats/{daemonId}")
    public Map<String, Object> getStats(@PathVariable Long daemonId) {
        return authService.getDaemonStats(daemonId);
    }


    @PostMapping("/report")
    public Report submitReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @GetMapping("/my-reports/{daemonId}")
    public java.util.List<Report> getMyReports(@PathVariable Long daemonId) {
        return reportService.getReportsByDaemon(daemonId);
    }
}
