package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskDto(@NotBlank String title) {}
