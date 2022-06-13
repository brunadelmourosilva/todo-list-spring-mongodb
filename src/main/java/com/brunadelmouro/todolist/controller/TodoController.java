package com.brunadelmouro.todolist.controller;

import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public ResponseEntity<?> findAllTodos(){
        List<TodoDTO> todoDTOList  = todoRepository.findAll();

        if(!todoDTOList.isEmpty())
            return new ResponseEntity<List<TodoDTO>>(todoDTOList, HttpStatus.OK);
        else
            return new ResponseEntity<>("No todos avaliable", HttpStatus.NOT_FOUND);
    }
}
