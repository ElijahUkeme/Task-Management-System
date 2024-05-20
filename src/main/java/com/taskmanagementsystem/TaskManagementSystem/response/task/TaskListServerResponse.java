package com.taskmanagementsystem.TaskManagementSystem.response.task;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskListServerResponse {

    private String terminus;
    private String status;
    private TaskListResponse response;
}
