package com.gyro.test_app.domain.dto;

import java.util.List;

public record TaskListDto(
        int id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks

)
{
}
