package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.TaskDTO;
import com.cbi.taskManager.model.Project;

import java.util.List;

public record ProjectDTO(Long id, String name, List<TaskDTO> tasks) {
    public ProjectDTO(Project project){
        this(
                project.getId(),
                project.getName(),
                project.getTasks().stream()
                        .map(TaskDTO::new)
                        .toList()
        );
    }
}
