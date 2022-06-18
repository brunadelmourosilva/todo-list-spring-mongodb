package com.brunadelmouro.todolist.controller;

import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.service.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({TodoController.class})
class TodoControllerTest {
//
//    static final String TODO_API = "/todos";
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    TodoService todoService;
//
//    @Autowired
//    ObjectMapper objectMapper;

//    String nowDate;
//    TodoDTO todoDTO1;
//    TodoDTO todoDTO2;
//    List<TodoDTO> todoDTOList;

//    @BeforeEach
//    void setUp() {
//        nowDate = String.valueOf(System.currentTimeMillis());
//
//        todoDTO1 = new TodoDTO("12345", "Study RabbitMQ", "Study this tech today", true, nowDate, nowDate);
//        todoDTO2 = new TodoDTO("54321", "Study REST Assured", "Study this tech today", false, nowDate, nowDate);
//
//        todoDTOList = List.of(todoDTO1, todoDTO2);
//    }

//    @AfterEach
//    void tearDown() {
//        reset(todoService);
//    }
//
//    @Test
//    void findAllTodos() throws Exception {
//        //given
//        given(todoService.getAllTodos()).willReturn(todoDTOList);
//
//        //when - then
//        mockMvc.perform(MockMvcRequestBuilders.get(TODO_API)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(objectMapper.writeValueAsString(todoDTOList)))
//
//                .andExpect(status().isOk());
//    }

    @Test
    void saveTodo() {
    }

    @Test
    void findTodoById() {
    }

    @Test
    void updateTodoById() {
    }

    @Test
    void deleteTodoById() {
    }
}