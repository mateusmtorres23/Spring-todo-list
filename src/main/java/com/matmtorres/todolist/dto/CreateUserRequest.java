package com.matmtorres.todolist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(

        @NotEmpty(message = "Email is required")
        @Email
        String email,
        @NotEmpty(message = "Name is required")
        String name,
        @NotEmpty(message = "Password is required")
        String password

) {}
