package com.gyro.test_app.domain.dto;

import com.gyro.test_app.domain.entities.TaskPriority;
import com.gyro.test_app.domain.entities.TaskStatus;

import java.time.LocalDateTime;

public record TaskDto(
        int id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
                      )
{


}
