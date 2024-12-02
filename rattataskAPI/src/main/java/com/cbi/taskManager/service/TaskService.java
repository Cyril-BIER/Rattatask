package com.cbi.taskManager.service;

import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.repository.ProjectRepository;
import com.cbi.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public void delete(List<Long> ids) {
        List<Task> tasks = taskRepository.findAllById(ids);
        for(Task task : tasks){
            Project project = task.getProject();
            List<Task> filteredTasks = project.getTasks().stream()
                    .filter(t-> !t.equals(task))
                    .toList();
            project.setTasks(filteredTasks);
            projectRepository.save(project);
        }
    }
}
