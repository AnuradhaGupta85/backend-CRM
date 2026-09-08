package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportDto(@NotBlank String title) {}
