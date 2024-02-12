package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.TodoCreationRequestDTO;
import com.danielbontii.tpms.dtos.TodoUpdateRequestDTO;
import com.danielbontii.tpms.models.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo findById(Long id);

    Todo save(TodoCreationRequestDTO todoRequest);

    boolean deleteById(Long id);

    Todo update(TodoUpdateRequestDTO todoUpdateRequestDTO);
}
