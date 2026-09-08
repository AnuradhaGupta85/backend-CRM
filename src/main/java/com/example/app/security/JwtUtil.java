package com.example.app.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final SecretKey key;
    public JwtUtil(@Value("${JWT_SECRET}") String secret) { this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); }
    public String generateToken(String email) { return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 1_800_000L)).signWith(key).compact(); }
    public String extractEmail(String token) { return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject(); }
    public boolean isValid(String token) { try { extractEmail(token); return true; } catch (RuntimeException exception) { return false; } }
}
