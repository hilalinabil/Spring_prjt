package com.gyro.test_app.domain.dto;

public record ErorrResponse(
        int status,
        String message,
        String details
        ) {


}
