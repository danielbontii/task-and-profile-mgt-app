package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.TodoCreationInput;
import com.danielbontii.tpms.dtos.TodoUpdateInput;
import com.danielbontii.tpms.models.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo findById(Long id);

    Todo save(TodoCreationInput todoRequest);

    boolean deleteById(Long id);

    Todo update(TodoUpdateInput todoUpdateInput);
}
