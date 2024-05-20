package com.taskmanagementsystem.TaskManagementSystem.response.task;

import com.taskmanagementsystem.TaskManagementSystem.model.task.TaskModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private int code;
    private String title;
    private String message;
    private TaskModel data;
}
