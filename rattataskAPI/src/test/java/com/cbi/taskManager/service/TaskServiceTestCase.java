package com.cbi.taskManager.service;

import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.repository.ProjectRepository;
import com.cbi.taskManager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTestCase {
    @Mock
    TaskRepository taskRepository;

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    TaskService taskService;

    @Test
    public void delete1Task() {
        Project project = new Project("Mon projet");
        project.addTasks(List.of(
                new Task(1L, "Tache1", "description", List.of()),
                new Task(2L, "Tache2", "description", List.of()),
                new Task(3L, "Tache3", "description", List.of())
        ));

        Project expected = new Project("Mon projet");
        expected.addTasks(List.of(
                new Task(1L, "Tache1", "description", List.of()),
                new Task(3L, "Tache3", "description", List.of())
        ));

        when(taskRepository.findAllById(List.of(2L)))
                .thenReturn(List.of(project.getTasks().get(1)));
        taskService.delete(List.of(2L));
        verify(projectRepository).saveAll(Set.of(expected));
    }

    @Test
    public void deleteManyTasks() {
        Project project = new Project("Mon projet");
        project.addTasks(List.of(
                new Task(1L, "Tache1", "description", List.of()),
                new Task(2L, "Tache2", "description", List.of()),
                new Task(3L, "Tache3", "description", List.of())
        ));

        Project expected = new Project("Mon projet");
        expected.addTasks(List.of(
                new Task(3L, "Tache3", "description", List.of())
        ));

        when(taskRepository.findAllById(List.of(1L, 2L)))
                .thenReturn(List.of(
                        project.getTasks().get(0),
                        project.getTasks().get(1)));
        taskService.delete(List.of(1L,2L));
        verify(projectRepository).saveAll(Set.of(expected));
    }

}
