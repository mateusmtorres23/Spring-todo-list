package com.matmtorres.todolist.dto.task;

import com.matmtorres.todolist.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskInfoResponse(

    UUID id,
    User owner,
    String title,
    LocalDateTime creationDate

) {}
