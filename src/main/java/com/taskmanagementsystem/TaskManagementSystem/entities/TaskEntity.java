package com.taskmanagementsystem.TaskManagementSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class TaskEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;
    private String title;
    private String  description;
    private boolean isComplete;
    private LocalDate dueDate;
    private Date createdAt;
    private Date updatedAt;
}
