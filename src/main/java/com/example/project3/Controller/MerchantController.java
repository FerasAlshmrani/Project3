package com.example.project3.Controller;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Category;
import com.example.project3.Model.Merchant;
import com.example.project3.Service.MerchantService;
import com.example.project3.Service.MerchantStockService;
import com.example.project3.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    // Q11
    private final MerchantStockService merchantStockService;
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getMerchants(){
        ArrayList<Merchant> merchants =merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean check = merchantService.addMerchant(merchant);

        if(check == false){
            return ResponseEntity.status(400).body(new ApiResponse("Must be UNIQUE ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@RequestBody @Valid Merchant merchant ,@PathVariable String id,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        int check = merchantService.updateMerchant(merchant,id);

        if(check == -1) {
            return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
        } else if (check == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("The new ID Must be UNIQUE ID"));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Updated"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        boolean check = merchantService.deleteMerchant(id);
        if(check) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
    }


}
