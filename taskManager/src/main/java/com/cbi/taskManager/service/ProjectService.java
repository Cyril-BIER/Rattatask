package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.ProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public Project createProject(ProjectDTO projectDTO) {
        return projectRepository.save(new Project(projectDTO.name()));
    }

    public List<Project> getProjects(List<Long> ids) {
        if(ids.isEmpty()) return projectRepository.findAll();
        return projectRepository.findAllById(ids);
    }
}
