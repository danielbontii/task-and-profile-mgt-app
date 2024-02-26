package com.danielbontii.tpms.controllers;

import com.danielbontii.tpms.constants.Authorities;
import com.danielbontii.tpms.dtos.TodoCreationInput;
import com.danielbontii.tpms.dtos.TodoUpdateInput;
import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.services.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @QueryMapping
    @Secured({Authorities.ADMIN, Authorities.USER})
    public List<Todo> allTodos(Authentication authentication) {
        return todoService.findAll(authentication);
    }

    @QueryMapping
    public Todo todoById(@Argument(name = "id") Long id) {
        return todoService.findById(id);
    }

    @MutationMapping
    public Todo createTodo(@Argument(name = "todo") @Valid TodoCreationInput todoCreationInput) {
        return todoService.save(todoCreationInput);
    }

    @MutationMapping
    public boolean deleteTodo(@Argument(name = "id") Long id) {
        return todoService.deleteById(id);
    }

    @MutationMapping
    public Todo updateTodo(@Argument(name = "todo") @Valid TodoUpdateInput todoUpdateInput) {
        return todoService.update(todoUpdateInput);
    }
}
