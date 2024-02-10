package com.danielbontii.tpms.services;

import com.danielbontii.tpms.models.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();
}
