package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificationDto(@NotBlank String message) {}
