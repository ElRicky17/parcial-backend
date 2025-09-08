package com.sinnoquierohacernada.parcialyfinal.dao;

import com.sinnoquierohacernada.parcialyfinal.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findBySubmittedById(Long userId);
}
