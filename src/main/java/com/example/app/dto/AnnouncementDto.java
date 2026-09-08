package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

public record AnnouncementDto(@NotBlank String title) {
}
