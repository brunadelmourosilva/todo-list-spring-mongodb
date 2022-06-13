package com.brunadelmouro.todolist;

import com.brunadelmouro.todolist.model.TodoDTO;
import com.brunadelmouro.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class TodoListApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Autowired
	TodoRepository todoRepository;

	@Override
	public void run(String... args) throws Exception {

//		TodoDTO todoDTO = new TodoDTO(null, "teste", "ts", true, LocalDateTime.now().toString(), LocalDateTime.now().toString());
//
//		todoRepository.save(todoDTO);
	}
}
