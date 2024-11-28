package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.ProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTestCase {
    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService;

    @Test
    public void postOneProjectTest(){
        ProjectDTO projectDTO = new ProjectDTO("My project");
        projectService.createProject(projectDTO);
        verify(projectRepository).save(new Project("My project"));
    }

    @Test
    public void getZeroProjectTest(){
        when(projectRepository.findAllById(List.of())).thenReturn(List.of());
        List<Project> actual = projectService.getProjects(List.of());
        assertEquals(List.of(),actual);
    }

    @Test
    public void getOneProjectTest(){
        Project project1 =new Project("Project 1");
        when(projectRepository.findAllById(List.of(1L))).thenReturn(List.of(
                project1
        ));
        List<Project> actual = projectService.getProjects(List.of(1L));
        assertEquals(List.of(project1),actual);
    }

    @Test
    public void getManyProjectTest(){
        Project project1 =new Project("Project 1");
        Project project2 =new Project("Project 2");
        when(projectRepository.findAllById(List.of())).thenReturn(List.of(project1, project2));
        List<Project> actual = projectService.getProjects(List.of());
        assertEquals(List.of(project1, project2),actual);
    }
}
