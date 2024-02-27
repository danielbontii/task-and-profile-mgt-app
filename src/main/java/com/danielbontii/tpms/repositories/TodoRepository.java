package com.danielbontii.tpms.repositories;

import com.danielbontii.tpms.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByTitleIgnoreCaseAndUserIdEquals(String title, Long userId);

    List<Todo> findByUserEmail(String userEmail);

    Optional<Todo> findByIdNotAndUserIdEqualsAndTitleIgnoreCase(Long todoId, Long userId, String title);
}