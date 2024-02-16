package com.epayplus.assessment.service;

import com.epayplus.assessment.dto.NewTaskRequest;
import com.epayplus.assessment.dto.UpdateTaskRequest;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity <?> createTask (NewTaskRequest request);
    ResponseEntity <?> getTasks (int page, int size);
    ResponseEntity <?> getTask (Long id);
    ResponseEntity <?> updateTask (UpdateTaskRequest request, Long id);
    ResponseEntity <?> deleteTask (Long id);
}
