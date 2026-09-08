package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartmentDto(@NotBlank String name) {
}
