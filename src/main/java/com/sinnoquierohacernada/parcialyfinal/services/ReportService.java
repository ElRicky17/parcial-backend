package com.sinnoquierohacernada.parcialyfinal.services;

import com.sinnoquierohacernada.parcialyfinal.dto.Auth.ReportDTO;
import com.sinnoquierohacernada.parcialyfinal.models.Report;
import com.sinnoquierohacernada.parcialyfinal.models.User;
import com.sinnoquierohacernada.parcialyfinal.dao.ReportRepository;
import com.sinnoquierohacernada.parcialyfinal.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;


    public ReportDTO createReport(ReportDTO dto) {
        User user = null;
        Report report = null;
        if ( dto.getUserId() != null) {
            user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            report = Report.builder()
                    .message(dto.getMessage())
                    .anonymous(dto.isAnonymous())
                    .user(user)
                    .build();
        }else {
            report = Report.builder()
                    .message(dto.getMessage())
                    .anonymous(dto.isAnonymous())
                    .user(null)
                    .build();

        }


        Report saved = reportRepository.save(report);
        return toDTO(saved);
    }

    public ReportDTO updateReport(UUID id, ReportDTO dto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        report.setMessage(dto.getMessage());
        report.setAnonymous(dto.isAnonymous());

        if (!dto.isAnonymous() && dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            report.setUser(user);
        } else {
            report.setUser(null);
        }

        return toDTO(reportRepository.save(report));
    }

    public void deleteReport(UUID id) {
        if (!reportRepository.existsById(id)) {
            throw new RuntimeException("Reporte no encontrado");
        }
        reportRepository.deleteById(id);
    }

    public List<ReportDTO> getReportsByUserId(UUID userId) {
        return reportRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



    private ReportDTO toDTO(Report report) {
        return ReportDTO.builder()
                .id(report.getId())
                .message(report.getMessage())
                .createdAt(report.getCreatedAt())
                .anonymous(report.isAnonymous())
                .userId(report.getUser() != null ? report.getUser().getId() : null)
                .build();
    }
}