package com.matmtorres.todolist.service;

import com.matmtorres.todolist.dto.task.CreateTaskRequest;
import com.matmtorres.todolist.dto.task.CreateTaskResponse;
import com.matmtorres.todolist.dto.task.TaskInfoResponse;
import com.matmtorres.todolist.model.Task;
import com.matmtorres.todolist.model.User;
import com.matmtorres.todolist.repository.TaskRepository;
import com.matmtorres.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public CreateTaskResponse createTask(CreateTaskRequest request) {

        Optional<User> user = userRepository.findById(request.userId());

        if(user.isEmpty()) {
            throw new IllegalArgumentException("User doesn't exist");
        }

        Task newTask = new Task(
                null,
                user.get(),
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
