package com.gyro.test_app.services.impl;

import com.gyro.test_app.domain.entities.Task;
import com.gyro.test_app.domain.entities.TaskPriority;
import com.gyro.test_app.domain.entities.TaskStatus;
import com.gyro.test_app.repositories.TaskListRepository;
import com.gyro.test_app.repositories.TaskRepository;
import com.gyro.test_app.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task already has an ID!");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title is required!");
        }
        TaskPriority taskPriority = Optional.
                ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        taskListRepository.findById(taskListId)
                .orElseThrow(()->new IllegalArgumentException("Invalid task list ID provided!"));

            LocalDateTime now = LocalDateTime.now();
        Task taskToSve = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now

        );
        return taskRepository.save(taskToSve);

    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdandId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()){
            throw new IllegalArgumentException("Task id is required!");
        }
        if(!Objects.equals(taskId, task.getId())){
             throw new IllegalArgumentException("Task id does not match!");
        }
        if (null == task.getPriority()) {
            throw new IllegalArgumentException("Task must have a valid priority!");
        }
        if(null == task.getStatus()) {
            throw new IllegalArgumentException("Task must have a valid status!");
        }

       Task existingTask = taskRepository.findByTaskListIdandId(taskListId, taskId)
                .orElseThrow(()->new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdandId(taskListId, taskId);
    }

}
