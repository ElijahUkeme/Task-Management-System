package com.taskmanagementsystem.TaskManagementSystem.service.main;

import com.taskmanagementsystem.TaskManagementSystem.dto.TaskDto;
import com.taskmanagementsystem.TaskManagementSystem.dto.TaskUpdateDto;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskListServerResponse;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskServerResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface TaskService {

    public TaskServerResponse createTask(TaskDto taskDto, HttpServletRequest request);
    public TaskServerResponse updateTask(TaskUpdateDto taskUpdateDto, HttpServletRequest request);
    public TaskServerResponse getATask(Integer taskId,HttpServletRequest request);
    public TaskServerResponse deleteATask(Integer taskId,HttpServletRequest request);
    public TaskListServerResponse getAllTask(HttpServletRequest request);
}
