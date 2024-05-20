package com.taskmanagementsystem.TaskManagementSystem.service.impl;


import com.taskmanagementsystem.TaskManagementSystem.dto.TaskDto;
import com.taskmanagementsystem.TaskManagementSystem.dto.TaskUpdateDto;
import com.taskmanagementsystem.TaskManagementSystem.entities.TaskEntity;
import com.taskmanagementsystem.TaskManagementSystem.repository.TaskRepository;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskListResponse;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskListServerResponse;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskResponse;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskServerResponse;
import com.taskmanagementsystem.TaskManagementSystem.service.main.TaskService;
import com.taskmanagementsystem.TaskManagementSystem.util.TaskModelUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    @Value("${baseUrl}")
    private String baseUrl;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskServerResponse createTask(TaskDto taskDto, HttpServletRequest request) {
        if (Objects.isNull(taskDto.getTitle())|| taskDto.getTitle().equalsIgnoreCase("")){
            return new TaskServerResponse(baseUrl+request.getRequestURI(),"NOT OK",new TaskResponse(
                    409,"Task Creation","Task title required",null
            ));
        }
        if (Objects.isNull(taskDto.getDescription())||taskDto.getDescription().equalsIgnoreCase("")){
            return new TaskServerResponse(baseUrl+request.getRequestURI(),"NOT OK",new TaskResponse(
                    409,"Task Creation","Task description required",null
            ));
        }
        if (Objects.isNull(taskDto.getDueDate())|| taskDto.getDueDate().isBefore(LocalDate.now())){
            return new TaskServerResponse(baseUrl+request.getRequestURI(),"NOT OK",new TaskResponse(
                    409,"Task Creation","A valid due date is required",null
            ));
        }

        TaskEntity task = TaskEntity.builder()
                .createdAt(new Date())
                .description(taskDto.getDescription())
                .title(taskDto.getTitle())
                .dueDate(taskDto.getDueDate())
                .isComplete(false)
                .updatedAt(null)
                .build();

        taskRepository.save(task);
        return new TaskServerResponse(baseUrl+request.getRequestURI(),"OK",new TaskResponse(
                201,"Task Creation","Task Created Successfully", TaskModelUtil.getReturnedTaskModel(task)
        ));
    }

    @Override
    public TaskServerResponse updateTask(TaskUpdateDto taskUpdateDto, HttpServletRequest request) {
        TaskEntity task = taskRepository.findByTaskId(taskUpdateDto.getTaskId());
        if (Objects.isNull(task)){
            return new TaskServerResponse(baseUrl+request.getRequestURI(),"NOT OK",new TaskResponse(
                    409,"Task Updating","Task Id not found",null
            ));
        }
        //only update the task if the user has provided the data for it
        //And also check if the user type only an empty space
        if (Objects.nonNull(taskUpdateDto.getDescription())&& !"".equalsIgnoreCase(taskUpdateDto.getDescription())){
            task.setDescription(taskUpdateDto.getDescription());
        }
        if (Objects.nonNull(taskUpdateDto.getDueDate())){
            task.setDueDate(taskUpdateDto.getDueDate());
        }
        if (Objects.nonNull(taskUpdateDto.getTitle())&& !"".equalsIgnoreCase(taskUpdateDto.getTitle())){
            task.setTitle(taskUpdateDto.getTitle());
        }
        task.setUpdatedAt(new Date());
        taskRepository.save(task);
        return new TaskServerResponse(baseUrl+request.getRequestURI(),"OK",new TaskResponse(
                200,"Task Updating","Task Updated Successfully", TaskModelUtil.getReturnedTaskModel(task)
        ));
    }

    @Override
    public TaskServerResponse getATask(Integer taskId,HttpServletRequest request) {
        TaskEntity task = taskRepository.findByTaskId(taskId);
        if (Objects.isNull(task)){
            return new TaskServerResponse(baseUrl+request.getRequestURI(),"NOT OK",new TaskResponse(
                    409,"Task Retrieval","Task Id not found",null
            ));
        }
        return new TaskServerResponse(baseUrl+request.getRequestURI(),"OK",new TaskResponse(
                200,"Task Retrieval","Task Retrieved Successfully", TaskModelUtil.getReturnedTaskModel(task)
        ));
    }

    @Override
    public TaskServerResponse deleteATask(Integer taskId, HttpServletRequest request) {
        TaskEntity task = taskRepository.findByTaskId(taskId);
        if (Objects.isNull(task)){
            return new TaskServerResponse(baseUrl+request.getRequestURI(),"NOT OK",new TaskResponse(
                    409,"Task Deletion","Task Id not found",null
            ));
        }
        taskRepository.delete(task);
        return new TaskServerResponse(baseUrl+request.getRequestURI(),"NOT OK",new TaskResponse(
                200,"Task Deletion","Task deleted Successfully",null
        ));
    }

    @Override
    public TaskListServerResponse getAllTask(HttpServletRequest request) {
        List<TaskEntity> taskEntityList = taskRepository.findAll();

        return new TaskListServerResponse(baseUrl+request.getRequestURI(),"OK",
                new TaskListResponse(200,"Task List","Your task list",TaskModelUtil.getReturnedTaskList(taskEntityList)));

    }
}
