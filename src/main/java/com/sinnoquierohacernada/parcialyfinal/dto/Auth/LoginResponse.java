package com.sinnoquierohacernada.parcialyfinal.dto.Auth;


import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponse {
    private String token;
    private String role;
    private UUID userId;
}