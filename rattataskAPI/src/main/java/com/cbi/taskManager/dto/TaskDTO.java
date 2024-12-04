package com.cbi.taskManager.dto;

import com.cbi.taskManager.enums.TaskStatus;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.model.User;

import java.util.List;

public record TaskDTO(Long id, String name, String description, TaskStatus status, List<Long> userIds) {
    public TaskDTO(Task task) {
        this(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getUsers().stream()
                        .map(User::getId) // Extracts `User` IDs.
                        .toList()
        );
    }
}

