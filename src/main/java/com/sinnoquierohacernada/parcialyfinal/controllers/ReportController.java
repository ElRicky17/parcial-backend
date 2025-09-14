package com.sinnoquierohacernada.parcialyfinal.controllers;


import com.sinnoquierohacernada.parcialyfinal.dto.Auth.ReportDTO;
import com.sinnoquierohacernada.parcialyfinal.models.Report;
import com.sinnoquierohacernada.parcialyfinal.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO dto) {
        ReportDTO created = reportService.createReport(dto);
        return ResponseEntity.ok(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReportDTO> updateReport(
            @PathVariable UUID id,
            @RequestBody ReportDTO dto) {
        ReportDTO updated = reportService.updateReport(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable UUID id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReportDTO>> getReportsByUser(@PathVariable UUID userId) {
        List<ReportDTO> reports = reportService.getReportsByUserId(userId);
        return ResponseEntity.ok(reports);
    }
    @GetMapping
    public List<ReportDTO> getReports() {
        return reportService.getAllReports();
    }

}