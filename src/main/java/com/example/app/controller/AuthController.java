package com.example.app.controller;

import com.example.app.dto.JwtResponse;
import com.example.app.dto.LoginRequest;
import com.example.app.dto.RegisterRequest;
import com.example.app.entity.AppUser;
import com.example.app.repository.AppUserRepository;
import com.example.app.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
public class AuthController {
  private final AppUserRepository users;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;

  public AuthController(AppUserRepository users, PasswordEncoder encoder, JwtUtil jwtUtil) {
    this.users = users;
    this.encoder = encoder;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/register")
  @Operation(summary = "Register user")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
    if (users.findByEmail(request.email()).isPresent())
      return ResponseEntity.status(409).body("Email already registered");
    AppUser user = new AppUser();
    user.setName(request.name());
    user.setEmail(request.email());
    user.setPassword(encoder.encode(request.password()));
    users.save(user);
    return ResponseEntity.ok(new JwtResponse(jwtUtil.generateToken(user.getEmail()), "Bearer"));
  }

  @PostMapping("/login")
  @Operation(summary = "Login user")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
    return users
        .findByEmail(request.email())
        .filter(user -> encoder.matches(request.password(), user.getPassword()))
        .<ResponseEntity<?>>map(
            user ->
                ResponseEntity.ok(
                    new JwtResponse(jwtUtil.generateToken(user.getEmail()), "Bearer")))
        .orElse(ResponseEntity.status(401).body("Invalid credentials"));
  }

  @GetMapping("/me")
  @Operation(summary = "Fetch current user profile")
  public ResponseEntity<?> me(Authentication authentication) {
    return users
        .findByEmail(authentication.getName())
        .<ResponseEntity<?>>map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/logout")
  @Operation(summary = "Logout user")
  public ResponseEntity<Void> logout() {
    return ResponseEntity.noContent().build();
  }
}
