package com.gyro.test_app.services;

import com.gyro.test_app.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {

    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);

}
