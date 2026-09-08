package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record MeetingDto(@NotBlank String title) {}
