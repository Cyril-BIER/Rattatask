package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.TaskDTO;
import com.cbi.taskManager.enums.TaskStatus;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.repository.TaskRepository;
import com.cbi.taskManager.repository.UserRepository;
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
    UserRepository userRepository;

    @InjectMocks
    TaskService taskService;


    @Test
    public void delete1Task() {
        taskService.delete(List.of(2L));
        verify(taskRepository).deleteAllById(List.of(2L));
    }

    @Test
    public void deleteManyTasks() {
        taskService.delete(List.of(1L,2L));
        verify(taskRepository).deleteAllById(List.of(1L, 2L));
    }

    @Test
    public void update1Task(){
        TaskDTO dto = new TaskDTO(1L,"","Nouvelle description", TaskStatus.ONGOING, List.of());
        Task orginal = new Task(1L, "Nom tâche", "Ancienne description",List.of());
        Task expected = new Task(1L, "Nom tâche", "Nouvelle description",List.of());
        expected.setStatus(TaskStatus.ONGOING);

        when(taskRepository.findAllById(List.of(1L))).thenReturn(List.of(orginal));
        when(userRepository.findAllById(Set.of())).thenReturn(List.of());
        taskService.updateTasks(List.of(dto));
        verify(taskRepository).saveAll(List.of(expected));
    }

    @Test
    public void updateManyTasks(){
        List<TaskDTO> dtos = List.of(
                new TaskDTO(1L,"","Nouvelle description de tâche 1", TaskStatus.ONGOING, List.of()),
                new TaskDTO(2L,"","Nouvelle description de tâche 2", TaskStatus.DONE, List.of())
        );

        List<Task> originals = List.of(
                new Task(1L, "Nom tâche 1", "Ancienne description 1",List.of()),
                new Task(2L, "Nom tâche 2", "Ancienne description 2",List.of())
        );
        Task expected1 = new Task(1L, "Nom tâche 1", "Nouvelle description de tâche 1",List.of());
        expected1.setStatus(TaskStatus.ONGOING);
        Task expected2 = new Task(2L, "Nom tâche 2", "Nouvelle description de tâche 2",List.of());
        expected2.setStatus(TaskStatus.DONE);

        when(taskRepository.findAllById(List.of(1L, 2L))).thenReturn(originals);
        when(userRepository.findAllById(Set.of())).thenReturn(List.of());

        taskService.updateTasks(dtos);
        verify(taskRepository).saveAll(List.of(expected1, expected2));
    }

}
