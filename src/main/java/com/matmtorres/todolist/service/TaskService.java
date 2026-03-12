package com.matmtorres.todolist.service;

import com.matmtorres.todolist.dto.task.CreateTaskRequest;
import com.matmtorres.todolist.dto.task.CreateTaskResponse;
import com.matmtorres.todolist.dto.task.TaskInfoResponse;
import com.matmtorres.todolist.model.Task;
import com.matmtorres.todolist.model.User;
import com.matmtorres.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public CreateTaskResponse createTask(CreateTaskRequest request, User user) {

        Task newTask = new Task(
                null,
                user,
                request.title(),
                request.description(),
                request.startAt(),
                request.endAt(),
                request.priorities(),
                null
        );

        taskRepository.save(newTask);
        return new CreateTaskResponse(newTask.getId(), newTask.getTitle());
    }

    public List<TaskInfoResponse> findTasks(UUID userId) {

        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .filter(task -> task.getUser().getId().equals(userId))
                .map(task -> new TaskInfoResponse(
                        task.getId(),
                        task.getUser(),
                        task.getTitle(),
                        task.getCreatedAt()
                ))
                .toList();

    }
}
