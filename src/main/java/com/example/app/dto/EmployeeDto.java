package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDto(@NotBlank String name) {
}
