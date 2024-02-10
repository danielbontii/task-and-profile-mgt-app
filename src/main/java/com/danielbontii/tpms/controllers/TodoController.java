package com.danielbontii.tpms.controllers;

import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
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
    public Todo todoById(@Argument Long id) {
        return todoService.findById(id);
    }

}
