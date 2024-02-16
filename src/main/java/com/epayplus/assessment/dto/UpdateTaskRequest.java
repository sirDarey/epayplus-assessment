package com.epayplus.assessment.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskRequest(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotBlank
        String status
) {
}
