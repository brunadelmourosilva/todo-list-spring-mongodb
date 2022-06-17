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

import java.util.List;
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

    TodoDTO todoDTO1;
    TodoDTO todoDTO2;

    List<TodoDTO> todos;
    String nowDate;

    @BeforeEach
    void setUp() {
        nowDate = String.valueOf(System.currentTimeMillis());

        todoDTO1 = new TodoDTO("62a7c51c541e3655708d6544", "Study algorithms and data structures", "Study COM112", false, nowDate, nowDate);
        todoDTO2 = new TodoDTO("62a7c51c541e3655708d0000", "Study OOP", "Study COM220", true, nowDate, nowDate);

        todos = List.of(todoDTO1, todoDTO2);
    }

    @Test
    void createTodo() {

    }

    @Test
    void getAllTodos() {

        //given
        given(todoRepository.findAll()).willReturn(todos);

        //when
        List<TodoDTO> responseList = todoService.getAllTodos();

        //then
        then(todoRepository).should(times(1)).findAll();

        assertEquals(2, responseList.size());
        assertEquals("Study algorithms and data structures", todos.get(0).getTodo());
        assertEquals("Study OOP", todos.get(1).getTodo());
    }

    @Test
    void getSingleTodoWhereIdWasFound() throws TodoCollectionException {
        //chamar as funções da camada mais inferior para a mais superior

        //given - dado uma condição
        given(todoRepository.findById(todoDTO1.getId())).willReturn(Optional.of(todoDTO1));

        //when - quando tal condição acontecer, guarda o resultado de acordo com o retorno do método
        TodoDTO response = todoService.getSingleTodo(todoDTO1.getId());

        //then - então garanta que os os resultados foram iguais
        then(todoRepository).should(times(1)).findById(todoDTO1.getId());

        //verificando se
        assertEquals("Study algorithms and data structures", response.getTodo());
        assertEquals("Study COM112", response.getDescription());
        assertEquals(false, response.getCompleted());
        assertEquals(nowDate, response.getCreatedAt());
    }

    @Test
    void getSingleTodoWhereIdWasNotFound() {

        //given
        given(todoRepository.findById(todoDTO1.getId())).willReturn(Optional.ofNullable(null));

        //when
        assertThrows(TodoCollectionException.class, () -> {
            todoService.getSingleTodo(todoDTO1.getId());
        });

        //then
        then(todoRepository).should(times(1)).findById(todoDTO1.getId());
    }

    @Test
    void updateTodo() {
    }

    @Test
    void deleteTodoById() {
    }
}