package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record LeaveDto(@NotBlank String reason) {
}
