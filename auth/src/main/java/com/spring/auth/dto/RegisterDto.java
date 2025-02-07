package com.spring.auth.dto;
import org.springframework.http.HttpStatus;
public class RegisterDto {
    public record RegisterQueryDTO(String username, String password) { }
    public record RegisterResponseDTO(HttpStatus code, String message) { }
}
