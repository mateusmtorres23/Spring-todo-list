package com.matmtorres.todolist.dto.task;

import java.util.UUID;

public record CreateTaskResponse(

   UUID id,
    String title

) {}
