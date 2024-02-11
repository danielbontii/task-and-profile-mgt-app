package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.TodoRequestDTO;
import com.danielbontii.tpms.models.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo findById(Long id);

    Todo save(TodoRequestDTO todoRequest);

    boolean deleteById(Long id);
}
