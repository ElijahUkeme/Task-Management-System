package com.taskmanagementsystem.TaskManagementSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private String title;
    private String  description;
    private LocalDate dueDate;
}
