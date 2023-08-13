package com.example.project3.Controller;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Product;
import com.example.project3.Model.User;
import com.example.project3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {



    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        ArrayList<User> users =userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUsers(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean check = userService.addUsers(user);
        if(check == false){
            return ResponseEntity.status(400).body(new ApiResponse("Must be UNIQUE ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@RequestBody @Valid User user ,@PathVariable String id,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        int check = userService.updateUsers(user,id);

        if(check == -1) {
            return ResponseEntity.status(400).body(new ApiResponse(" Wrong ID"));
        }else if (check == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("New ID must be UNIQUE"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        boolean check = userService.deleteUsers(id);
        if(check) {
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
    }

    @PutMapping("/buy/{id}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String id,@PathVariable String productId,@PathVariable String merchantId){

        int checkNumber = userService.addProduct(id,productId,merchantId);

        if(checkNumber == -1){
            return ResponseEntity.status(400).body(new ApiResponse("User id NOT FOUND"));
        } else if(checkNumber == 3){
            return ResponseEntity.status(400).body(new ApiResponse("both MerchantId and ProductId NOT FOUND"));
        }else if (checkNumber == 2){
            return ResponseEntity.status(400).body(new ApiResponse("MarchentId NOT FOUND"));
        }else if(checkNumber == 1){
            return ResponseEntity.status(400).body(new ApiResponse("ProductId NOT FOUND"));
        }else if(checkNumber == 4) {
            return ResponseEntity.status(400).body(new ApiResponse("this product is out of stock"));
        }else if(checkNumber == 5) {
            return ResponseEntity.status(400).body(new ApiResponse("you dont have much money "));
        }else{
            return ResponseEntity.status(200).body(new ApiResponse("Found ids ,User bought added "));
        }
    }
}
