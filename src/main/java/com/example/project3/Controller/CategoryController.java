package com.example.project3.Controller;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Category;
import com.example.project3.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> categories =categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean check = categoryService.addCategory(category);
        if(check == false){
            return ResponseEntity.status(400).body(new ApiResponse("Must be UNIQUE ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Categary added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@RequestBody @Valid Category category ,@PathVariable String id,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        int check = categoryService.updateCategory(category,id);
        if(check == -1) {
            return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
        } else if (check == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("The new ID Must be UNIQUE ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Categary Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        boolean check = categoryService.deleteCategory(id);
        if(check) {
            return ResponseEntity.status(200).body(new ApiResponse("Categary Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
    }




}
