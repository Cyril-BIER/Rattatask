package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.ProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTestCase {
    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService;

    @Test
    public void postOneProject(){
        ProjectDTO projectDTO = new ProjectDTO("My project");
        projectService.createProject(projectDTO);
        verify(projectRepository).save(new Project("My project"));
    }
}
