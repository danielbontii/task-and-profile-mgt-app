package com.danielbontii.tpms.services.impl;

import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.repositories.TodoRepository;
import com.danielbontii.tpms.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }
}
