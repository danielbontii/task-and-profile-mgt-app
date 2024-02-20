package com.danielbontii.tpms.mappers;

import com.danielbontii.tpms.dtos.TodoCreationInput;
import com.danielbontii.tpms.dtos.TodoUpdateInput;
import com.danielbontii.tpms.models.Todo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoMapper {

    public static Todo toTodo(TodoCreationInput todoCreationInput) {
        Todo todo = new Todo();
        todo.setTitle(todoCreationInput.getTitle().trim());
        todo.setCompleted(todoCreationInput.isCompleted());

        if (!Objects.isNull(todoCreationInput.getDescription())) {
            todo.setDescription(todoCreationInput.getDescription().trim());
        }

        return todo;
    }

    public static Todo toUpdatedTodo(Todo todoToUpdate, TodoUpdateInput todoUpdateInput) {
        todoToUpdate.setTitle(todoUpdateInput.getTitle().trim());
        if (!Objects.isNull(todoUpdateInput.getDescription())) {
            todoToUpdate.setDescription(todoUpdateInput.getDescription().trim());
        }
        todoToUpdate.setCompleted(todoUpdateInput.isCompleted());

        return todoToUpdate;
    }
}
