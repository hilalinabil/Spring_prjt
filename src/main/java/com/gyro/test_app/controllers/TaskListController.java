package com.gyro.test_app.controllers;

import com.gyro.test_app.domain.dto.TaskListDto;
import com.gyro.test_app.domain.entities.TaskList;
import com.gyro.test_app.mappers.TaskListMapper;
import com.gyro.test_app.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @GetMapping(path="/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id")UUID tasklistid) {
        return taskListService.getTaskList(tasklistid).map(taskListMapper::toDto);
    }
    @PutMapping(path="/{task_list_id}")
    public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskListDto taskListDto
    ){
        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(updatedTaskList);
    }
    @DeleteMapping(path = "/{task_List_Id}")
    public void deleteTaskList(@PathVariable("task_List_Id") UUID taskListId)
    {
        taskListService.deleteTaskList(taskListId);
    }

}
