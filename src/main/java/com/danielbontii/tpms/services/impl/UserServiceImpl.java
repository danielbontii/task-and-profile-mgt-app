package com.danielbontii.tpms.services.impl;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponse;
import com.danielbontii.tpms.exceptions.AlreadyExistsException;
import com.danielbontii.tpms.mappers.UserMapper;
import com.danielbontii.tpms.models.CustomUserDetails;
import com.danielbontii.tpms.models.User;
import com.danielbontii.tpms.repositories.UserRepository;
import com.danielbontii.tpms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponse save(UserCreationInput userCreationInput) {
        userRepository.findByEmail(userCreationInput.getEmail())
                .ifPresent(user -> {
                    throw new AlreadyExistsException("email already taken");
                });
        User newUser = userMapper.toUser(userCreationInput);
        User savedUser = userRepository.save(newUser);

        //TODO: Send registration email
        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username.toLowerCase());

        return userOptional
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
