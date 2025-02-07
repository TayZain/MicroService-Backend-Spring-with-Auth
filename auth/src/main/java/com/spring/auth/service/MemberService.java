package com.spring.auth.service;
import com.spring.auth.dto.LoginDto;
import com.spring.auth.dto.RegisterDto;
import com.spring.auth.entities.Member;
import com.spring.auth.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class MemberService {

    private final String secretKey;

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.secretKey = "4enGpPAg6GSKkQn1dnEBmPzrNWNhbIVkF2gMpzWqVDvHFAJZiQtnS46DkmPr3vMyFkBEtYshlKtmghF16MYEBPHlLSPUc1sXANgSHvSYktU=";
    }

    public LoginDto.LoginResponseDTO login(String username, String password) {
        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            return new LoginDto.LoginResponseDTO(HttpStatus.NOT_FOUND, "User not found", null);
        }

        if (!member.getPassword().equals(password)) {
            return new LoginDto.LoginResponseDTO(HttpStatus.UNAUTHORIZED, "Invalid password", null);
        }

        String token = generateToken(username);

        return new LoginDto.LoginResponseDTO(HttpStatus.OK, "Login successful", token);
    }

    public RegisterDto.RegisterResponseDTO register(String username, String password) {
        if (memberRepository.existsByUsername(username)) {
            return new RegisterDto.RegisterResponseDTO(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        Member newMember = new Member();
        newMember.setUsername(username);
        newMember.setPassword(password);

        memberRepository.save(newMember);


        return new RegisterDto.RegisterResponseDTO(HttpStatus.CREATED, "User registered successfully");
    }


    public String generateToken(String username) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        Key k = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
                .signWith(k)
                .compact();
    }

}