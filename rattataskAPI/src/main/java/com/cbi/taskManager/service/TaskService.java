package com.cbi.taskManager.service;

import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.repository.ProjectRepository;
import com.cbi.taskManager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Transactional
    public void delete(List<Long> ids) {
        List<Task> tasks = taskRepository.findAllById(ids);
        Set<Project> projects = tasks.stream()
                .map(Task::getProject)
                .peek(project -> project.setTasks(
                        project.getTasks().stream()
                                .filter(t->!tasks.contains(t))
                                .toList()))
                .collect(Collectors.toSet());
        projectRepository.saveAll(projects);
    }
}
