package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.CreateTaskDTO;
import com.cbi.taskManager.dto.ProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.ProjectRepository;
import com.cbi.taskManager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;

    public Project createProject(ProjectDTO projectDTO) {
        return projectRepository.save(new Project(projectDTO.name()));
    }

    public List<Project> getProjects(List<Long> ids) {
        if(ids.isEmpty()) return projectRepository.findAll();
        return projectRepository.findAllById(ids);
    }

    public List<Task> addTasks(Long projectID, List<CreateTaskDTO> dtos) {
        List<Task> tasks = new ArrayList<>();
        for(CreateTaskDTO dto:dtos){
            List<User> users = userRepository.findAllById(dto.usersID());
            Task task = new Task(dto.name(),dto.description(), users);
            tasks.add(task);
        }
        Project project = projectRepository.findById(projectID).orElseThrow(EntityNotFoundException::new);
        project.addTasks(tasks);
        projectRepository.save(project);
        return tasks;
    }
}
