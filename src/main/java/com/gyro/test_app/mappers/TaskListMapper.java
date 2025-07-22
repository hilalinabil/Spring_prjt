package com.gyro.test_app.mappers;

import com.gyro.test_app.domain.dto.TaskListDto;
import com.gyro.test_app.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
