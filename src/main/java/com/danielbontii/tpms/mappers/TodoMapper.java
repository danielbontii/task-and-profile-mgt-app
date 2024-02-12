package com.danielbontii.tpms.mappers;

import com.danielbontii.tpms.dtos.TodoRequestDTO;
import com.danielbontii.tpms.models.Todo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoMapper {

    public static Todo toTodo(TodoRequestDTO todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle().trim());
        todo.setCompleted(todoRequest.isCompleted());

        if (!Objects.isNull(todoRequest.getDescription())) {
            todo.setDescription(todoRequest.getDescription().trim());
        }

        return todo;
    }
}
