package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.TodoCreationInput;
import com.danielbontii.tpms.dtos.TodoUpdateInput;
import com.danielbontii.tpms.models.Todo;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TodoService {

    /**
     * Returns all todos for a single user or for all users
     *
     * @param authentication the authentication of the user
     * @return list of todos for a user or for all users
     */
    List<Todo> findAll(Authentication authentication);

    /**
     * Returns a single todo by ID
     *
     * @param id             the todo ID
     * @param authentication the authentication of the user
     * @return a todo
     */
    Todo findById(Long id, Authentication authentication);

    /**
     * Creates and returns a todo
     *
     * @param todoRequest the details of the todo
     * @return Todo the newly created todo
     */
    Todo save(TodoCreationInput todoRequest);

    /**
     * Deletes a todo
     *
     * @param id             the id of the todo
     * @param authentication the authentication of the user
     * @return a boolean
     */
    boolean deleteById(Long id, Authentication authentication);

    /**
     * Updates a todo
     *
     * @param todoUpdateInput the details of the todo
     * @param authentication  the authentication of the user
     * @return the updated todo
     */
    Todo update(TodoUpdateInput todoUpdateInput, Authentication authentication);
}
