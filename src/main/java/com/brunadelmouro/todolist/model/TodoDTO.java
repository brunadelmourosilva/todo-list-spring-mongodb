package com.brunadelmouro.todolist.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO {

    @Id
    private String id;

    @NotNull(message = "todo cannot be null")
    private String todo;

    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "completed cannot be null")
    private Boolean completed;

    private String createdAt;
    private String updatedAt;
}
