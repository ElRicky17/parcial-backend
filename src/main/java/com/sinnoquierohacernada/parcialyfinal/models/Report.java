package com.sinnoquierohacernada.parcialyfinal.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;  // el mensaje del reporte

    private boolean anonymous = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Relación opcional con el usuario que lo envía (para Daemons)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User submittedBy;
}
