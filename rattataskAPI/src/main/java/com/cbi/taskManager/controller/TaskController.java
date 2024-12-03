package com.cbi.taskManager.controller;

import com.cbi.taskManager.dto.UpdateTaskDTO;
import com.cbi.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @DeleteMapping
    public ResponseEntity<?> deleteTask(
            @RequestParam(name="ids")List<Long> ids
            ){
        try {
            taskService.delete(ids);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> putTask(
            @RequestBody List<UpdateTaskDTO> dtos
    ){
        try {
            return ResponseEntity.ok(taskService.updateTasks(dtos));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
