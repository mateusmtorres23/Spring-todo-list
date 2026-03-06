package com.matmtorres.todolist.service;

import com.matmtorres.todolist.dto.CreateUserRequest;
import com.matmtorres.todolist.dto.CreateUserResponse;
import com.matmtorres.todolist.model.User;
import com.matmtorres.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {

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
