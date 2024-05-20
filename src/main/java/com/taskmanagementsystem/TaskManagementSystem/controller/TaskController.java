package com.taskmanagementsystem.TaskManagementSystem.controller;


import com.taskmanagementsystem.TaskManagementSystem.dto.TaskDto;
import com.taskmanagementsystem.TaskManagementSystem.dto.TaskUpdateDto;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskListServerResponse;
import com.taskmanagementsystem.TaskManagementSystem.response.task.TaskServerResponse;
import com.taskmanagementsystem.TaskManagementSystem.service.main.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/task/create")
    public TaskServerResponse createATask(@RequestBody TaskDto taskDto, HttpServletRequest request){
        return taskService.createTask(taskDto,request);
    }



    @PostMapping("/task/update")
    public TaskServerResponse updateATask(@RequestBody TaskUpdateDto taskUpdateDto,HttpServletRequest request){
        return taskService.updateTask(taskUpdateDto,request);
    }

    @PostMapping("/task/delete/{taskId}")
    public TaskServerResponse deleteATask(@PathVariable("taskId")Integer taskId,HttpServletRequest request){
        return taskService.deleteATask(taskId,request);
    }

    @PostMapping("/task/get/{taskId}")
    public TaskServerResponse getATask(@PathVariable("taskId")Integer taskId,HttpServletRequest request){
        return taskService.getATask(taskId,request);
    }

    @GetMapping("/task/all")
    public TaskListServerResponse getAllTask(HttpServletRequest request){
        return taskService.getAllTask(request);
    }


    @MessageMapping("/task.sendTask")
    @SendTo("/topic/public")
    public TaskDto sendTask(@Payload TaskDto task){
        return task;
    }

    @MessageMapping("/task.addTask")
    @SendTo("/topic/public")
    public TaskDto addTask(@Payload TaskDto task, SimpMessageHeaderAccessor accessor){
        accessor.getSessionAttributes().put("task",task.getTitle());
        return task;
    }
}


