package com.spring.auth.dto;

import org.springframework.http.HttpStatus;

public class LoginDto {

    public record LoginQueryDTO(String username, String password) { }
    public record LoginResponseDTO(HttpStatus code, String message, String token) { }
}

