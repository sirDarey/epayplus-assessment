package com.epayplus.assessment.controller;

import com.epayplus.assessment.dto.NewTaskRequest;
import com.epayplus.assessment.dto.UpdateTaskRequest;
import com.epayplus.assessment.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity <?> createTask (@RequestBody @Valid NewTaskRequest request) {
        return taskService.createTask(request);
    }


    @GetMapping
    public ResponseEntity <?> getTasks (
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10")int size
    ) {
        return taskService.getTasks(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> getTask (@PathVariable Long id) {
        return taskService.getTask(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity <?> updateTask (@RequestBody @Valid UpdateTaskRequest request, @PathVariable Long id) {
        return taskService.updateTask(request, id);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteTask (@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
