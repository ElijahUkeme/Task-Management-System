package com.taskmanagementsystem.TaskManagementSystem.util;

import com.taskmanagementsystem.TaskManagementSystem.entities.TaskEntity;
import com.taskmanagementsystem.TaskManagementSystem.model.task.TaskModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskModelUtil {

    //It is not a good practice to return your entity
    // as a response object so that is why I am creating
    //a separate class called TaskModel for my response.

    //this method takes a parameter of task entity
    // and convert it to an object of task model
    public static TaskModel getReturnedTaskModel(TaskEntity task){
        TaskModel taskModel = TaskModel.builder()
                .id(task.getTaskId())
                .createdAt(task.getCreatedAt())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .updatedAt(task.getUpdatedAt())
                .isComplete(checkForTaskCompletion(task.getDueDate()))
                .build();
        return taskModel;
    }


    //this method convert a list of task entity
    //into a list of a task model and return it
    public static List<TaskModel> getReturnedTaskList(List<TaskEntity> taskEntityList){
        List<TaskModel>  taskModels = new ArrayList<>();
        for (TaskEntity task: taskEntityList){
            taskModels.add(getReturnedTaskModel(task));
        }
        return taskModels;
    }

    static boolean checkForTaskCompletion(LocalDate date){
        if (date.isBefore(LocalDate.now())){
            return true;
        }
        return false;
    }
}
