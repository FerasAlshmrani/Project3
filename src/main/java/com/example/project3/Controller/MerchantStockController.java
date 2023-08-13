package com.example.project3.Controller;

import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.Category;
import com.example.project3.Model.MerchantStock;
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
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {


    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        ArrayList<MerchantStock> merchantStocks =merchantStockService.getMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
// check productId and merchantId
        int check = merchantStockService.addMerchantStock(merchantStock);
        if(check == 4){
            return ResponseEntity.status(400).body(new ApiResponse("Must be UNIQUE ID"));
        }
        if(check == 1){
            return ResponseEntity.status(400).body(new ApiResponse("Both MerchantId and ProductId NOT FOUND"));
        }else if(check == 2){
            return ResponseEntity.status(400).body(new ApiResponse("MerchantId NOT FOUND"));
        }else if(check == 3){
            return ResponseEntity.status(400).body(new ApiResponse("ProductId NOT FOUND"));
        }else

        return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@RequestBody @Valid MerchantStock merchantStock ,@PathVariable String id,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        int checkNumber = merchantStockService.updateMerchantStock(merchantStock,id);
        if(checkNumber == -1) {
            return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
        } else if (checkNumber == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("Both MerchantId and ProductId not found"));
        } else if (checkNumber == 2) {
            return ResponseEntity.status(400).body(new ApiResponse(" MerchantId NOT FOUND"));
        } else if (checkNumber == 3) {
            return ResponseEntity.status(400).body(new ApiResponse(" ProductId NOT FOUND"));
        }else if (checkNumber == 4) {
            return ResponseEntity.status(400).body(new ApiResponse(" it must be UNIQUE ID"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("MerchantStock Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id){
        boolean check = merchantStockService.deleteMerchantStock(id);
        if(check) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
    }

    @PutMapping("/add-stock/{id}/{productId}/{merchantId}/{stock}")
    public ResponseEntity addMerchantStock(@PathVariable String id,@PathVariable String productId,@PathVariable String merchantId,@PathVariable int stock){

        int checkNumber = merchantStockService.addMerhcant(id,productId,merchantId,stock);

        if(checkNumber == -1){
            return ResponseEntity.status(400).body(new ApiResponse("MerchantStockId NOT FOUND"));
        } else if(checkNumber == 3){
            return ResponseEntity.status(400).body(new ApiResponse("both marchentId and ProductId NOT FOUND"));
        }else if (checkNumber == 2){
            return ResponseEntity.status(400).body(new ApiResponse("MarchentId NOT FOUND"));
        }else if(checkNumber == 1){
            return ResponseEntity.status(400).body(new ApiResponse("ProductId NOT FOUND"));
        }else {
            return ResponseEntity.status(200).body(new ApiResponse("Found ids ,MerchantStocks Added "));
        }
    }




}
