package com.example.project3.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "name must not be empty")
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 3, message = "name have to be more than 3 length long.")
    private String name;
}
