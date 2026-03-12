package com.matmtorres.todolist.dto.task;

import com.matmtorres.todolist.model.TaskPriorities;

import java.time.LocalDateTime;

public record CreateTaskRequest(

   String title,
   String description,
   LocalDateTime startAt,
   LocalDateTime endAt,
   TaskPriorities priorities

) {}
