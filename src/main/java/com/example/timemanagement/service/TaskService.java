package com.example.timemanagement.service;

import com.example.timemanagement.DTO.TaskDto;
import com.example.timemanagement.entity.Task;
import com.example.timemanagement.enums.Status;

import java.util.List;

public interface TaskService {



    //    Task createTask (Long userId, TaskDto taskDto);
    Task createTask (TaskDto taskDto);

    List<Task> viewTasks();


    Task viewParticularTask (Long taskId);

    Task viewTaskByStatus(Status status);



    void deleteTask(Long taskId) ;


    Task updateTask(Long taskId, TaskDto taskDto);

}

