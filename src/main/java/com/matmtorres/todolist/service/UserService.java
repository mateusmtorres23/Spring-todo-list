package com.matmtorres.todolist.service;

import com.matmtorres.todolist.dto.user.CreateUserRequest;
import com.matmtorres.todolist.dto.user.CreateUserResponse;
import com.matmtorres.todolist.dto.user.UserInfoResponse;
import com.matmtorres.todolist.model.User;
import com.matmtorres.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponse registerUser(CreateUserRequest request) {

        userRepository.findUserByEmail(request.email()).ifPresent(user -> {
            throw new IllegalStateException("Email already in use");
        });

        User newUser = new User(
                null,
                request.name(),
                request.email(),
                request.password(),
                null
        );

        userRepository.save(newUser);
        return new CreateUserResponse(request.email(), request.name());
    }

}
