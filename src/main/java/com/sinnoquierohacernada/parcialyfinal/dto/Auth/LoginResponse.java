package com.sinnoquierohacernada.parcialyfinal.dto.Auth;


import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String role;
}