package com.matmtorres.todolist.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserInfoResponse(

        UUID id,
        String name,
        String email,
        LocalDateTime creationDate

) {}
