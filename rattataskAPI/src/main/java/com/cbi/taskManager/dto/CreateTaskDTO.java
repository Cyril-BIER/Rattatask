package com.cbi.taskManager.dto;

import java.util.List;

public record CreateTaskDTO(String name, String description, List<Long> usersID) {
}
