package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.CreateTaskDTO;
import com.cbi.taskManager.dto.CreateProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.ProjectRepository;
import com.cbi.taskManager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;

    public Project createProject(CreateProjectDTO createProjectDTO) {
        return projectRepository.save(new Project(createProjectDTO.name()));
    }

    public List<ProjectDTO> getProjects(List<Long> ids) {
        if(ids.isEmpty()) return projectRepository.findAll().stream()
                .map(ProjectDTO::new)
                .toList();
        return projectRepository.findAllById(ids).stream()
                .map(ProjectDTO::new)
                .toList();
    }

    @Transactional
    public List<Task> addTasks(Long projectID, List<CreateTaskDTO> dtos) {
        Set<Long> usersID = dtos.stream()
                .map(CreateTaskDTO::usersID)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
        List<User> allUsers = userRepository.findAllById(usersID);

        List<Task> tasks = dtos.stream()
                .map(dto->
                        new Task(dto.name(), dto.description(),
                            allUsers.stream()
                                    .filter(user -> dto.usersID().contains(user.getId()))
                                    .toList()))
                .toList();
        Project project = projectRepository.findById(projectID).orElseThrow(EntityNotFoundException::new);
        project.addTasks(tasks);
        projectRepository.save(project);
        return tasks;
    }

    @Transactional
    public void delete(List<Long> ids) {
        projectRepository.deleteAllById(ids);
    }
}
