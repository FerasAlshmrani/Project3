package com.example.project3.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "id must not be empty")
    private String id;

    @NotEmpty(message = "username must not be empty")
    @Size(min = 5 ,message = "username must at least have more than 5 length long")
    private String username;

    @NotEmpty(message = "password must not be empty")
    @Size(min = 6,message = "Password must at least have more than 6 length long ")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",message = "must have characters and digit ")
    private String password;

    @Email(message = "must be valid email")
    @NotEmpty(message = "email must not be empty")
    private String email;

    @NotEmpty(message = "role must not be empty")
    @Pattern(regexp = "(?:Admin|Customer)",message = "must be Admin or Customer")
    private String role;

    @NotNull(message = "balance must be not null")
    @Positive(message = "have to be positive")
    private Integer balance;

}
