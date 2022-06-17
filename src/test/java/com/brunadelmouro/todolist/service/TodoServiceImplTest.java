package com.brunadelmouro.todolist.service;

import com.brunadelmouro.todolist.exception.TodoCollectionException;
import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoServiceImpl todoService;

    TodoDTO todoDTO;
    String nowDate;

    @BeforeEach
    void setUp() {
        nowDate = String.valueOf(System.currentTimeMillis());
        todoDTO = new TodoDTO("62a7c51c541e3655708d6544", "Study algorithms and data structures", "Study COM112", false, nowDate, nowDate);
    }

    @Test
    void createTodo() {
    }

    @Test
    void getAllTodos() {
    }

    @Test
    void getSingleTodoWhereIdWasFound() throws TodoCollectionException {
        //chamar as funções da camada mais inferior para a mais superior

        //given - dado uma condição
        given(todoRepository.findById(todoDTO.getId())).willReturn(Optional.of(todoDTO));

        //when - quando tal condição acontecer, guarda o resultado de acordo com o retorno do método
        TodoDTO response = todoService.getSingleTodo(todoDTO.getId());

        //then - então garanta que os os resultados foram iguais
        then(todoRepository).should(times(1)).findById(todoDTO.getId());

        //verificando se
        assertEquals("Study algorithms and data structures", response.getTodo());
        assertEquals("Study COM112", response.getDescription());
        assertEquals(false, response.getCompleted());
        assertEquals(nowDate, response.getCreatedAt());
    }

    @Test
    void getSingleTodoWhereIdWasNotFound() {

        //given
        //given(todoRepository.findById(todoDTO.getId())).willReturn(Optional.of(todoDTO));

        //when
        assertThrows(TodoCollectionException.class, () -> {
            todoService.getSingleTodo(todoDTO.getId());
        });

        //then
        then(todoRepository).should(times(1)).findById(todoDTO.getId());
    }

    @Test
    void updateTodo() {
    }

    @Test
    void deleteTodoById() {
    }
}