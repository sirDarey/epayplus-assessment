package com.epayplus.assessment.dto;

public record NewTaskRequest(


        String title,
        String description,
        String status
) {
}
