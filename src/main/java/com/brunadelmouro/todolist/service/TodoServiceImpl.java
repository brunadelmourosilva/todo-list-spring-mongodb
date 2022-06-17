package com.brunadelmouro.todolist.service;

import com.brunadelmouro.todolist.exception.TodoCollectionException;
import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException{
        Optional<TodoDTO> todoVerify = todoRepository.findByTodo(todoDTO.getTodo());
        if(todoVerify.isPresent())
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        else {
            todoDTO.setCreatedAt(String.valueOf(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
        }
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todos = todoRepository.findAll();

        if(!todos.isEmpty())
            return todos;
        else
            return new ArrayList<>();
    }

    @Override
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
        Optional<TodoDTO> optionalTodoDTO = todoRepository.findById(id);

        if(!optionalTodoDTO.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        } else {
            return optionalTodoDTO.get();
        }
    }

    @Override
    public void updateTodo(String id, TodoDTO todoDTO) throws TodoCollectionException {
        Optional<TodoDTO> todoDTOOptional = todoRepository.findById(id);
        Optional<TodoDTO> todoDTOOptionalSameName = todoRepository.findByTodo(todoDTO.getTodo());

        if(todoDTOOptionalSameName.isPresent() && !todoDTOOptionalSameName.get().equals(id))
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());

        if(todoDTOOptional.isPresent()){
            TodoDTO todoUpdated = todoDTOOptional.get();

            todoUpdated.setTodo(todoDTO.getTodo());
            todoUpdated.setDescription(todoDTO.getDescription());
            todoUpdated.setCompleted(todoDTO.getCompleted());
            todoUpdated.setUpdatedAt(String.valueOf(new Date(System.currentTimeMillis())));

            todoRepository.save(todoUpdated);
        } else {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }
}
