package com.danielbontii.tpms.services.impl;

import com.danielbontii.tpms.dtos.TodoRequestDTO;
import com.danielbontii.tpms.dtos.TodoUpdateRequestDTO;
import com.danielbontii.tpms.exceptions.AlreadyExistsException;
import com.danielbontii.tpms.exceptions.NotFoundException;
import com.danielbontii.tpms.mappers.TodoMapper;
import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.models.User;
import com.danielbontii.tpms.repositories.TodoRepository;
import com.danielbontii.tpms.repositories.UserRepository;
import com.danielbontii.tpms.services.TodoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    private static final String TODO_WITH_ID_NOT_FOUND = "Todo with id %d not found";

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TODO_WITH_ID_NOT_FOUND.formatted(id)));
    }

    @Override
    @Transactional
    public Todo save(TodoRequestDTO todoRequest) {
        User todoOwner = userRepository.findById(todoRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Invalid user id"));

        todoRepository.findByTitleIgnoreCase(todoRequest.getTitle()).ifPresent(
                todo -> {
                    throw new AlreadyExistsException("Todo with title " + todoRequest.getTitle() + " exists");
                });

        Todo newTodo = TodoMapper.toTodo(todoRequest);
        newTodo.setUser(todoOwner);
        return todoRepository.save(newTodo);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {

        Todo todoToDelete = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TODO_WITH_ID_NOT_FOUND.formatted(id)));
        todoRepository.delete(todoToDelete);

        return true;
    }

    @Override
    public Todo update(TodoUpdateRequestDTO todoUpdateRequestDTO) {
        Long todoToUpdateId = todoUpdateRequestDTO.getId();
        Todo todoToUpdate = todoRepository.findById(todoToUpdateId)
                .orElseThrow(() -> new NotFoundException(TODO_WITH_ID_NOT_FOUND.formatted(todoToUpdateId)));

        Todo updatedTodo = TodoMapper.toUpdatedTodo(todoToUpdate, todoUpdateRequestDTO);

        String updatedTitle = updatedTodo.getTitle();

        todoRepository.findByTitleIgnoreCase(updatedTitle).ifPresent(
                todo -> {
                    throw new AlreadyExistsException("Todo with title " + updatedTitle + " exists");
                });
        return todoRepository.save(updatedTodo);
    }
}
