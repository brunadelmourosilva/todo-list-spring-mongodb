package com.brunadelmouro.todolist.controller;

import com.brunadelmouro.todolist.exception.TodoCollectionException;
import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.repository.TodoRepository;
import com.brunadelmouro.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

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
            todoService.createTodo(todoDTO);
            return new ResponseEntity<>(todoDTO, HttpStatus.OK);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTodoById(@PathVariable String id){
        Optional<TodoDTO> todoDTO = todoRepository.findById(id);

        if(todoDTO.isPresent()){
            return new ResponseEntity<>(todoDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("Todo not found with id %s", id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodoById(@RequestBody TodoDTO newTodoDTO, @PathVariable String id){
        Optional<TodoDTO> todoDTO = todoRepository.findById(id);

        if(todoDTO.isPresent()){
            TodoDTO todoSave = todoDTO.get();
            todoSave.setCompleted(newTodoDTO.getCompleted() != null ? newTodoDTO.getCompleted() : todoSave.getCompleted());
            todoSave.setTodo(newTodoDTO.getTodo() != null ? newTodoDTO.getTodo() : todoSave.getTodo());
            todoSave.setDescription(newTodoDTO.getDescription() != null ? newTodoDTO.getDescription() : todoSave.getDescription());
            todoSave.setUpdatedAt(String.valueOf(new Date(System.currentTimeMillis())));
            todoRepository.save(todoSave);
            return new ResponseEntity<>(todoSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("Todo not found with id %s", id), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable String id){
        try{
            todoRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted with id " + id, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Todo not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
