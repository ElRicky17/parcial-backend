package com.sinnoquierohacernada.parcialyfinal.dto.Auth;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {
    private UUID id;
    private String message;
    private LocalDateTime createdAt;
    private boolean anonymous;
    private UUID userId;
}
