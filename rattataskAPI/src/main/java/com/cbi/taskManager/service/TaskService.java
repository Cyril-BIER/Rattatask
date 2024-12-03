package com.cbi.taskManager.service;

import com.cbi.taskManager.dto.UpdateTaskDTO;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.model.User;
import com.cbi.taskManager.repository.TaskRepository;
import com.cbi.taskManager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void delete(List<Long> ids) {
        taskRepository.deleteAllById(ids);
    }

    @Transactional
    public List<Task> updateTasks(List<UpdateTaskDTO> dtos) {
        List<Task> tasks = taskRepository.findAllById(
                dtos.stream()
                        .mapToLong(UpdateTaskDTO::id)
                        .boxed()
                        .toList()
        );

        List<User> users = userRepository.findAllById(
                dtos.stream()
                    .flatMap(d-> d.userIds().stream())
                    .collect(Collectors.toSet())
        );

        List<Task> updatedTasks = tasks.stream()
                .peek(task -> {
                    UpdateTaskDTO dto = dtos.stream()
                            .filter(d -> d.id().equals(task.getId()))
                            .findFirst()
                            .orElseThrow(EntityNotFoundException::new);

                    task.setDescription(dto.description());
                    task.setStatus(dto.status());
                    task.setUsers(
                            new ArrayList<>(users.stream()
                                    .filter(u -> dto.userIds().contains(u.getId()))
                                    .toList()
                            )
                    );

                })
                .toList();

        return taskRepository.saveAll(updatedTasks);
    }
}
