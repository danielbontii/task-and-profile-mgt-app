package com.danielbontii.tpms;

import com.danielbontii.tpms.models.Todo;
import com.danielbontii.tpms.models.User;
import com.danielbontii.tpms.repositories.TodoRepository;
import com.danielbontii.tpms.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;
import java.util.Optional;

@SpringBootApplication
public class TaskAndProfileManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskAndProfileManagementSystemApplication.class, args);
    }

    //TODO: Remove once I start adding users
    @Bean
    CommandLineRunner userAndTodoSeeder(UserRepository userRepository, TodoRepository todoRepository) {

        return args -> {

            User savedBertha = null;

            Optional<User> berthaOptional = userRepository.findByEmail("berthaboxer@admin.com");

            if (berthaOptional.isPresent()) {
                savedBertha = berthaOptional.get();
            }

            if (berthaOptional.isEmpty()) {
                User bertha = new User();
                bertha.setFirstName("Bertha");
                bertha.setLastName("Boxer");
                bertha.setEmail("berthaboxer@admin.com");
                bertha.setPassword("$2y$10$aRBQJR64w3DsQBIRIfPNL.oPD27bXQ5ws643BUka76bdnQ4UttR.e");
                savedBertha = userRepository.save(bertha);

            }

            if (todoRepository.findByTitleIgnoreCase("Title 1").isEmpty()) {
                Todo todo = new Todo();
                todo.setUser(savedBertha);
                todo.setTitle("Title 1");
                todo.setDescription("Description 1");
                todoRepository.save(todo);
            }

            if (todoRepository.findByTitleIgnoreCase("Title 2").isEmpty()) {
                Todo todo = new Todo();
                todo.setUser(savedBertha);
                todo.setTitle("Title 2");
                todo.setDescription("Description 2");
                todoRepository.save(todo);
            }

        };

    }

}
