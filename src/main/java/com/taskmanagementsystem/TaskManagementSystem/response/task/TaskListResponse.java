package com.taskmanagementsystem.TaskManagementSystem.response.task;

import com.taskmanagementsystem.TaskManagementSystem.model.task.TaskModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskListResponse {

    private int code;
    private String title;
    private String message;
    private List<TaskModel> data;
}
