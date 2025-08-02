package com.gyro.test_app.mappers;

import com.gyro.test_app.domain.dto.TaskDto;
import com.gyro.test_app.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto  taskDto);
    TaskDto toDto(Task task);




}
