package com.sinnoquierohacernada.parcialyfinal.services;

import com.sinnoquierohacernada.parcialyfinal.dao.ReportRepository;
import com.sinnoquierohacernada.parcialyfinal.models.Report;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    public List<Report> getReportsByDaemon(Long daemonId) {
        return reportRepository.findBySubmittedById(daemonId);
    }

}
