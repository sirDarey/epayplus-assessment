package com.epayplus.assessment.dto;

public record TaskResponse(

        Long id,
        String title,
        String description,
        String status
) {
}
