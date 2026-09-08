package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record LeadDto(@NotBlank String name) {
}
