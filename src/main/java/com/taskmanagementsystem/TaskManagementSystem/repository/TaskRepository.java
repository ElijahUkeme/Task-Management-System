package com.taskmanagementsystem.TaskManagementSystem.repository;

import com.taskmanagementsystem.TaskManagementSystem.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
    TaskEntity findByTaskId(int taskId);
}
