package com.matmtorres.todolist.controller;

import com.matmtorres.todolist.dto.task.TaskInfoResponse;
import com.matmtorres.todolist.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private TaskService taskService;

    @GetMapping
    public List<TaskInfoResponse> getTasks() {

        List<TaskInfoResponse> tasks = taskService.findTasks(userid)

    }

}
