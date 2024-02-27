package com.danielbontii.tpms.services.impl;

import com.danielbontii.tpms.constants.Authorities;
import com.danielbontii.tpms.dtos.TodoCreationInput;
import com.danielbontii.tpms.dtos.TodoUpdateInput;
import com.danielbontii.tpms.exceptions.AlreadyExistsException;
import com.danielbontii.tpms.exceptions.NotFoundException;
import com.danielbontii.tpms.mappers.TodoMapper;
import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.models.User;
import com.danielbontii.tpms.repositories.TodoRepository;
import com.danielbontii.tpms.repositories.UserRepository;
import com.danielbontii.tpms.services.TodoService;
import com.danielbontii.tpms.utils.AuthorizationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    private static final String TODO_WITH_ID_NOT_FOUND = "Todo with id %d not found";
    private static final String ACCESS_DENIED = "Access Denied";

    @Override
    public List<Todo> findAll(Authentication authentication) {

        if (!AuthorizationUtils.permits(authentication, Authorities.ADMIN)) {
            Jwt principal = AuthorizationUtils.getPrincipal(authentication);
            return todoRepository.findByUserEmail(principal.getClaimAsString("sub"));
        }

        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id, Authentication authentication) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TODO_WITH_ID_NOT_FOUND.formatted(id)));

        if (!AuthorizationUtils.authorizesTodoManipulation(authentication, todo) &&
                !AuthorizationUtils.permits(authentication, Authorities.ADMIN)) {
            throw new AccessDeniedException(ACCESS_DENIED);
        }

        return todo;
    }

    @Override
    @Transactional
    public Todo save(TodoCreationInput todoRequest) {
        User todoOwner = userRepository.findById(todoRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Invalid user id"));

        todoRepository.findByTitleIgnoreCaseAndUserIdEquals(todoRequest.getTitle(), todoOwner.getId()).ifPresent(
                todo -> {
                    throw new AlreadyExistsException("Todo with title " + todoRequest.getTitle() + " exists");
                });

        Todo newTodo = TodoMapper.toTodo(todoRequest);
        newTodo.setUser(todoOwner);
        return todoRepository.save(newTodo);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id, Authentication authentication) {

        Todo todoToDelete = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TODO_WITH_ID_NOT_FOUND.formatted(id)));

        if (!AuthorizationUtils.authorizesTodoManipulation(authentication, todoToDelete)) {
            throw new AccessDeniedException(ACCESS_DENIED);
        }
        todoRepository.delete(todoToDelete);

        return true;
    }

    @Override
    public Todo update(TodoUpdateInput todoUpdateInput, Authentication authentication) {
        Long todoToUpdateId = todoUpdateInput.getId();
        Todo todoToUpdate = todoRepository.findById(todoToUpdateId)
                .orElseThrow(() -> new NotFoundException(TODO_WITH_ID_NOT_FOUND.formatted(todoToUpdateId)));

        if (!AuthorizationUtils.authorizesTodoManipulation(authentication, todoToUpdate)) {
            throw new AccessDeniedException(ACCESS_DENIED);
        }

        Todo updatedTodo = TodoMapper.toUpdatedTodo(todoToUpdate, todoUpdateInput);

        String updatedTitle = updatedTodo.getTitle();

        todoRepository.findByIdNotAndUserIdEqualsAndTitleIgnoreCase(updatedTodo.getId(), updatedTodo.getUser().getId(), updatedTitle)
                .ifPresent(todo -> {
                    throw new AlreadyExistsException("Todo with title " + updatedTitle + " exists");
                });
        return todoRepository.save(updatedTodo);
    }
}
