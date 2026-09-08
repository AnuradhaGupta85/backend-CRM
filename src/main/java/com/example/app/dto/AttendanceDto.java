package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record AttendanceDto(@NotBlank String date) {}
