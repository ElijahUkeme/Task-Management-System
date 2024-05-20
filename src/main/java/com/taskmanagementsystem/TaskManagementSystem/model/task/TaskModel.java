package com.taskmanagementsystem.TaskManagementSystem.model.task;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {

    private Integer id;
    private String title;
    private String  description;
    private boolean isComplete;
    private LocalDate dueDate;
    private Date createdAt;
    private Date updatedAt;
}
