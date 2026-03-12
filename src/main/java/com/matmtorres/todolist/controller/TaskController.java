package com.matmtorres.todolist.controller;

import com.matmtorres.todolist.dto.task.CreateTaskRequest;
import com.matmtorres.todolist.dto.task.CreateTaskResponse;
import com.matmtorres.todolist.dto.task.TaskInfoResponse;
import com.matmtorres.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));

    }

}
