package com.gyro.test_app.controllers;

import com.gyro.test_app.domain.dto.TaskListDto;
import com.gyro.test_app.domain.entities.TaskList;
import com.gyro.test_app.mappers.TaskListMapper;
import com.gyro.test_app.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper  taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }


    @GetMapping
    public List<TaskListDto> lisTaskLists() {
       return  taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto).toList();

    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
      TaskList createdTaskList =  taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
              return taskListMapper.toDto(createdTaskList);
    }

}
