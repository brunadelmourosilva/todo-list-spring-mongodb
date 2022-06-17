package com.brunadelmouro.todolist.service;

import com.brunadelmouro.todolist.exception.TodoCollectionException;
import com.brunadelmouro.todolist.model.TodoDTO;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface TodoService {

    void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException;

    List<TodoDTO> getAllTodos();

    TodoDTO getSingleTodo(String id) throws TodoCollectionException;
}
