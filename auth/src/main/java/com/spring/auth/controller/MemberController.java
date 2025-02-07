package com.spring.auth.controller;
import com.spring.auth.dto.LoginDto;
import com.spring.auth.dto.RegisterDto;
import com.spring.auth.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDto.RegisterResponseDTO> register(@RequestBody RegisterDto.RegisterQueryDTO registerDTO) {
        RegisterDto.RegisterResponseDTO registerResponse = memberService.register(registerDTO.username(), registerDTO.password());

        return ResponseEntity.status(registerResponse.code()).body(registerResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDto.LoginResponseDTO> login(@RequestBody LoginDto.LoginQueryDTO loginQueryDTO) {
        LoginDto.LoginResponseDTO loginResponse = memberService.login(loginQueryDTO.username(), loginQueryDTO.password());

        return ResponseEntity.status(loginResponse.code()).body(loginResponse);
    }

}
