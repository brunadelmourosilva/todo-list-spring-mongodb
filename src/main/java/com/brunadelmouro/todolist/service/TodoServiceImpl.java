package com.brunadelmouro.todolist.service;

import com.brunadelmouro.todolist.exception.TodoCollectionException;
import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException{
        if(todoRepository.findByTodo(todoDTO.getTodo()))
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        else {
            todoDTO.setCreatedAt(String.valueOf(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
        }
    }
}
