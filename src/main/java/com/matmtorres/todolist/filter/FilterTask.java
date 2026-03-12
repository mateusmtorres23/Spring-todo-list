package com.matmtorres.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.matmtorres.todolist.model.User;
import com.matmtorres.todolist.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Component
public class FilterTask extends OncePerRequestFilter {

    private UserRepository userRepository;

    public  FilterTask(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var serveletPath = request.getServletPath();

        if (serveletPath.equals("/api/tasks")) {
            String baseRequest = request.getHeader("Authorization").substring("Basic".length()).trim();

            byte[] baseBytes = Base64.getDecoder().decode(baseRequest);

            String[] credentials = new String(baseBytes).split(":");
            String email = credentials[0];
            String password = credentials[1];

            Optional<User> user = userRepository.findUserByEmail(email);

            if (user.isEmpty()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            var passwordVerified = BCrypt.verifyer().verify(password.toCharArray(), user.get().getPasswordHash());

            if (!passwordVerified.verified) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            filterChain.doFilter(request, response);
        }
    }
}