package com.cbi.taskManager.dto;

import com.cbi.taskManager.enums.TaskStatus;

import java.util.List;

public record UpdateTaskDTO(Long id, String description, TaskStatus status,
                            List<Long> userIds) { }
