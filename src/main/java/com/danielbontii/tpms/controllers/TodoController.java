package com.danielbontii.tpms.controllers;

import com.danielbontii.tpms.dtos.TodoRequestDTO;
import com.danielbontii.tpms.dtos.TodoUpdateRequestDTO;
import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @QueryMapping
    public List<Todo> allTodos() {
        return todoService.findAll();
    }

    @QueryMapping
    public Todo todoById(@Argument(name = "id") Long id) {
        return todoService.findById(id);
    }

    @MutationMapping
    public Todo createTodo(@Argument(name = "todo") TodoRequestDTO todoRequest) {
        return todoService.save(todoRequest);
    }

    @MutationMapping
    public boolean deleteTodo(@Argument(name = "id") Long id) {
        return todoService.deleteById(id);
    }

    @MutationMapping
    public Todo updateTodo(@Argument(name = "todo") TodoUpdateRequestDTO todoUpdateRequestDTO) {
        return todoService.update(todoUpdateRequestDTO);
    }
}
