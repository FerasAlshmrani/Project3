package com.example.project3.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

// turing langauge alle feha 3mlyat 7sabyh mthl java python
// non turing language alle mafeha 3mlyat 7sabeyh mthl xml html

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "id must not be empty")
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 3 , message = "name must be more than 3 length")
    private String name;

    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive number")
    private Integer price;

    @NotEmpty(message = "categoryId must not be empty")
    private String categoryId;




}
