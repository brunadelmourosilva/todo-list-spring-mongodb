package com.brunadelmouro.todolist.controller;

import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<?> findAllTodos(){
        List<TodoDTO> todoDTOList  = todoRepository.findAll();

        if(!todoDTOList.isEmpty())
            return new ResponseEntity<List<TodoDTO>>(todoDTOList, HttpStatus.OK);
        else
            return new ResponseEntity<>("No todos avaliable", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveTodo(@RequestBody TodoDTO todoDTO){
        try{
            todoDTO.setCreatedAt(LocalDateTime.now().toString());
            return new ResponseEntity<>(todoRepository.save(todoDTO), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
