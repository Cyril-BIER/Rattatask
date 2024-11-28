package com.cbi.taskManager.controller;

import com.cbi.taskManager.dto.ProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> postProject(@RequestBody ProjectDTO dto){
        return ResponseEntity.ok(projectService.createProject(dto));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(@RequestParam(name = "id", defaultValue ="") List<Long> ids){
        return ResponseEntity.ok(projectService.getProjects(ids));
    }
}
