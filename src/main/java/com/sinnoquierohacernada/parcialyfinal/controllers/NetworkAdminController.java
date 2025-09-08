package com.sinnoquierohacernada.parcialyfinal.controllers;


import com.sinnoquierohacernada.parcialyfinal.models.Report;
import com.sinnoquierohacernada.parcialyfinal.services.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/network")
@PreAuthorize("hasAuthority('NETWORK_ADMIN')")
public class NetworkAdminController {
    private final ReportService reportService;

    public NetworkAdminController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("/resistance")
    public List<String> getResistancePage() {
        return List.of(
                "🔥 Tip: Nunca reveles tu contraseña a un Daemon.",
                "😂 Meme: 'Cuando Andrei intenta hacer DevOps... Fails!'"
        );
    }

    @PostMapping("/anonymous-report")
    public Report submitAnonymousReport(@RequestBody Report report) {
        report.setAnonymous(true);
        return reportService.saveReport(report);
    }
}
