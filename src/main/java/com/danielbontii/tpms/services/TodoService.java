package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.TodoCreationInput;
import com.danielbontii.tpms.dtos.TodoUpdateInput;
import com.danielbontii.tpms.models.Todo;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TodoService {

    List<Todo> findAll(Authentication authentication);

    Todo findById(Long id, Authentication authentication);

    Todo save(TodoCreationInput todoRequest);

    boolean deleteById(Long id, Authentication authentication);

    Todo update(TodoUpdateInput todoUpdateInput, Authentication authentication);
}
