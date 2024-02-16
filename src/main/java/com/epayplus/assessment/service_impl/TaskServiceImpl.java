package com.epayplus.assessment.service_impl;

import com.epayplus.assessment.dto.NewTaskRequest;
import com.epayplus.assessment.dto.ResponseDTO;
import com.epayplus.assessment.dto.TaskResponse;
import com.epayplus.assessment.dto.UpdateTaskRequest;
import com.epayplus.assessment.entity.Task;
import com.epayplus.assessment.exceptions.CustomException;
import com.epayplus.assessment.repo.TaskRepo;
import com.epayplus.assessment.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    @Transactional
    public ResponseEntity<?> createTask(NewTaskRequest request) {
        if (taskRepo.existsByTitle(request.title())) {
            throw new CustomException(400, "Title already exists");
        }

        Task savedTask = taskRepo.save(new Task(
                null,
                request.title(),
                request.description(),
                request.status(),
                ZonedDateTime.now()
        ));

        return ResponseEntity.status(201).body(new ResponseDTO<>(
                "00",
                "success",
                "New task created successfully",
                new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(),
                        savedTask.getStatus())
        ));
    }

    @Override
    public ResponseEntity<?> getTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page <Task> tasks = taskRepo.findAll(pageable);
        return ResponseEntity.ok(new ResponseDTO<>(
                "00",
                "success",
                "Tasks retrieved successfully",
                tasks.getContent()
        ));
    }

    @Override
    public ResponseEntity<?> getTask(Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(()-> new CustomException(
                        404, "Task not found"
                ));

        return ResponseEntity.ok(new ResponseDTO<>(
                "00",
                "success",
                "Task retrieved successfully",
                new TaskResponse(task.getId(), task.getTitle(), task.getDescription(),
                        task.getStatus())
        ));
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateTask(UpdateTaskRequest request, Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(()-> new CustomException(
                        404, "Task not found"
                ));

        task.setDescription(request.description());
        task.setTitle(request.title());
        task.setStatus(request.status());

        return ResponseEntity.ok(new ResponseDTO<>(
                "00",
                "success",
                "Task updated successfully",
                new TaskResponse(task.getId(), task.getTitle(), task.getDescription(),
                        task.getStatus())
        ));
    }

    @Override
    public ResponseEntity<?> deleteTask(Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(()-> new CustomException(
                        404, "Task not found"
                ));

        taskRepo.delete(task);
        return ResponseEntity.ok(new ResponseDTO<>(
                "00",
                "success",
                "Task deleted successfully",
                null
        ));
    }
}
