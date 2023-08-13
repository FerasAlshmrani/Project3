package com.example.project3.Service;

import com.example.project3.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {



    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    // Q11
    private final MerchantService merchantService;
    private final ProductService productService;




    public ArrayList<MerchantStock> getMerchantStock(){
        return merchantStocks;
    }

    public int addMerchantStock(MerchantStock merchantStock){
        // Must be UNIQUE ID
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(merchantStock.getId())){
                return 4;
            }
        }
        boolean checkMerchant = false;
        for (int i = 0; i < merchantService.getMerchants().size(); i++) {
            if(merchantService.getMerchants().get(i).getId().equals(merchantStock.getMerchantId())){
                checkMerchant = true;
            }
        }

        boolean checkProduct = false;
        for (int i = 0; i < productService.getProduct().size(); i++) {
            if(productService.getProduct().get(i).getId().equals(merchantStock.getProductId())){
                checkProduct = true;
            }
        }
        // both merchantids and productid not found
        if(checkMerchant == false && checkProduct == false){
            return 1;
        } else if (checkMerchant == false) { // merchantId not found
            return 2;
        } else if (checkProduct == false) { // product id not found
            return 3;
        }

        merchantStocks.add(merchantStock);

        return 0;
    }

    public int updateMerchantStock(MerchantStock merchantStock,String id){

        boolean idFound = false;
        int index = -1;
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                idFound = true;
                index = i;
                break;
            }
        }
        // id that you want to search for not found
        if(idFound == false){
            return -1;
        }

        boolean checkMerchant = false;
        for (int i = 0; i < merchantService.getMerchants().size(); i++) {
            if(merchantService.getMerchants().get(i).getId().equals(merchantStock.getMerchantId())){
                checkMerchant = true;
            }
        }

        boolean checkProduct = false;
        for (int i = 0; i < productService.getProduct().size(); i++) {
            if(productService.getProduct().get(i).getId().equals(merchantStock.getProductId())){
                checkProduct = true;
            }
        }
        // both merchantids and productid not found
        if(checkMerchant == false && checkProduct == false){
            return 1;
        } else if (checkMerchant == false) { // merchantId not found
            return 2;
        } else if (checkProduct == false) { // productId not found
            return 3;
        }

        // chack if there are another categoryId because its must be UNIQUE ID
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(merchantStock.getId())){
                // if the update are the same id continue but if he finds another one return new ID must be UNIQUE
                if(merchantStocks.get(index).getId().equals(merchantStock.getId())){
                    continue;
                }else{
                    return 4;
                }
            }
        }
        merchantStocks.set(index,merchantStock);
        return 0;

    }

    public boolean deleteMerchantStock(String id){

        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public int addMerhcant(String id, String productId , String merchantId, Integer stock){

        boolean checkProduct = false;

        int index =-1;
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                index=i;
                break;
            }
        }

        // if id not found return ( id not found)
        if(index == -1){
            return -1;
        }

        for (int i = 0; i < productService.getProduct().size(); i++) {
            if(productService.getProduct().get(i).getId().equals(productId)){
                checkProduct = true;
                break;
            }
        }

        boolean checkMerchant = false;

        for (int i = 0; i <merchantService.getMerchants().size(); i++) {
            if(merchantService.getMerchants().get(i).getId().equals(merchantId)){
                checkMerchant = true;
                break;
            }
        }
        // if they are both not found (return all not found)
        if(checkProduct == false && checkMerchant == false){
            return 3;
        }
        //if the merchantId not found return( merchantId not found)
        if(checkMerchant == false){
            return 2;
        }
        //if the productId not found return ( productId not found )
        if(checkProduct == false){
            return 1;
        }

        merchantStocks.get(index).setStock(merchantStocks.get(index).getStock()+stock);

        return 0;

    }
}