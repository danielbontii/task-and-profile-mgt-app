package com.danielbontii.tpms.mappers;

import com.danielbontii.tpms.dtos.TodoCreationRequestDTO;
import com.danielbontii.tpms.dtos.TodoUpdateRequestDTO;
import com.danielbontii.tpms.models.Todo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoMapper {

    public static Todo toTodo(TodoCreationRequestDTO todoCreationRequestDTO) {
        Todo todo = new Todo();
        todo.setTitle(todoCreationRequestDTO.getTitle().trim());
        todo.setCompleted(todoCreationRequestDTO.isCompleted());

        if (!Objects.isNull(todoCreationRequestDTO.getDescription())) {
            todo.setDescription(todoCreationRequestDTO.getDescription().trim());
        }

        return todo;
    }

    public static Todo toUpdatedTodo(Todo todoToUpdate, TodoUpdateRequestDTO todoUpdateRequestDTO) {
        todoToUpdate.setTitle(todoUpdateRequestDTO.getTitle().trim());
        if (!Objects.isNull(todoUpdateRequestDTO.getDescription())) {
            todoToUpdate.setDescription(todoUpdateRequestDTO.getDescription().trim());
        }
        todoToUpdate.setCompleted(todoUpdateRequestDTO.isCompleted());

        return todoToUpdate;
    }
}
