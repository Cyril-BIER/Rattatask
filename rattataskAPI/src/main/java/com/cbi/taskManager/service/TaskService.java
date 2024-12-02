package com.cbi.taskManager.service;

import com.cbi.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public void delete(List<Long> ids) {
        taskRepository.deleteAllById(ids);
    }
}
