package com.cbi.taskManager.controller;

import com.cbi.taskManager.dto.CreateTaskDTO;
import com.cbi.taskManager.dto.CreateProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.model.Task;
import com.cbi.taskManager.service.ProjectDTO;
import com.cbi.taskManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> postProject(@RequestBody CreateProjectDTO dto){
        return ResponseEntity.ok(projectService.createProject(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjects(@RequestParam(name = "id", defaultValue ="") List<Long> ids){
        return ResponseEntity.ok(projectService.getProjects(ids));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProjects(@RequestParam(name = "id") List<Long> ids){
        try{
            projectService.delete(ids);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<List<Task>> addTasks(
            @RequestParam(name = "projectID") Long projectId,
            @RequestBody List<CreateTaskDTO> dtos
    ){
        return ResponseEntity.ok(projectService.addTasks(projectId,dtos));
    }
}
