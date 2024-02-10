package com.danielbontii.tpms.services.impl;

import com.danielbontii.tpms.dtos.TodoRequestDTO;
import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.models.User;
import com.danielbontii.tpms.repositories.TodoRepository;
import com.danielbontii.tpms.repositories.UserRepository;
import com.danielbontii.tpms.services.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final ObjectMapper objectMapper;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Todo save(TodoRequestDTO todoRequest) {
        Optional<User> userOptional = userRepository.findById(todoRequest.getUserId());

        //Todo: Handle thrown errors
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user id");
        }

        todoRepository.findByTitle(todoRequest.getTitle()).ifPresent(
                todo -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "todo with title " + todoRequest.getTitle() + " exists");
                });

        User todoOwner = userOptional.get();
        Todo newTodo = objectMapper.convertValue(todoRequest, Todo.class);
        newTodo.setUser(todoOwner);
        return todoRepository.save(newTodo);
    }
}
