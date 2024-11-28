package com.cbi.taskManager.controller;

import com.cbi.taskManager.dto.ProjectDTO;
import com.cbi.taskManager.model.Project;
import com.cbi.taskManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> postProject(@RequestBody ProjectDTO dto){
        return ResponseEntity.ok(projectService.createProject(dto));
    }
}
