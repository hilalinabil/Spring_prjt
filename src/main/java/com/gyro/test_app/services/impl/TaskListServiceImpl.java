package com.gyro.test_app.services.impl;

import com.gyro.test_app.domain.entities.TaskList;
import com.gyro.test_app.repositories.TaskListRepository;
import com.gyro.test_app.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if( taskList.getId() != 0){
            throw new IllegalArgumentException("Task list already have an ID!");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title is required!");
        }

        LocalDateTime now = LocalDateTime.now();

      return  taskListRepository.save( new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }
}
