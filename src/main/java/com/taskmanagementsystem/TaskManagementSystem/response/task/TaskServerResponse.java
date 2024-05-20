package com.taskmanagementsystem.TaskManagementSystem.response.task;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskServerResponse {

    private String terminus;
    private String status;
    private TaskResponse response;
}
