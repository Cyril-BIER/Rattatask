package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.UpdateTaskDTO;
import com.cbi.taskManager.enums.TaskStatus;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTestCase {
    @Mock
    TaskRepository taskRepository;

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
        UpdateTaskDTO dto = new UpdateTaskDTO(1L,"Nouvelle description", TaskStatus.ONGOING, List.of());
        Task expected = new Task(1L, "Nom tâche", "Nouvelle description",List.of());
        expected.setStatus(TaskStatus.ONGOING);
        taskService.updateTasks(List.of(dto));
        verify(taskRepository).saveAll(List.of(expected));
    }

}
