package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Product;
import com.example.project3.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> products =productService.getProduct();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        int check = productService.addProduct(product);
        if(check == 2){
            return ResponseEntity.status(400).body(new ApiResponse("Must be UNIQUE ID"));
        }else if(check == 0){
            return ResponseEntity.status(200).body(new ApiResponse("Product added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("no CatogaryId found"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@RequestBody @Valid Product product ,@PathVariable String id,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        int intCatogary = productService.updateProduct(product,id);
        if(intCatogary == -1) {
            return ResponseEntity.status(400).body(new ApiResponse("Wrong ProductId"));
        } else if (intCatogary == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("no CatogaryId found"));
        }else if (intCatogary == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Must be UNIQUE ID"));
        }else{
        return ResponseEntity.status(200).body(new ApiResponse("Product Updated"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        boolean check = productService.deleteProduct(id);
        if(check) {
            return ResponseEntity.status(200).body(new ApiResponse("Product Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
    }

}


