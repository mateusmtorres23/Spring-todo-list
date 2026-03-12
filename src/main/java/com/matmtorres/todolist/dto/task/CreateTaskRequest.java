package com.matmtorres.todolist.dto.task;

import com.matmtorres.todolist.model.TaskPriorities;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTaskRequest(

   String title,
   UUID userId,
   String description,
   LocalDateTime startAt,
   LocalDateTime endAt,
   TaskPriorities priorities

) {}
