package com.example.project3.Service;

import com.example.project3.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {


    ArrayList<User> users = new ArrayList<>();

    private final ProductService productService;

    private final MerchantService merchantService;

    private final MerchantStockService merchantStockService;

    public ArrayList<User> getUsers(){
        return users;
    }

    public boolean addUsers(User user){
        // Must be UNIQUE ID
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(user.getId())){
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public int updateUsers(User user,String id){

        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                index = i;
                break;
            }
        }

        if(index == -1){
            return -1;
        }
        // chack if there another categoryId because its must be UNIQUE ID
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(user.getId())){
                // if the update are the same id continue but if he finds another one return new ID must be UNIQUE
                if(users.get(index).getId().equals(user.getId())){
                    continue;
                }else{
                    return 1;
                }
            }
        }
        users.set(index,user);
        return 0;

    }

    public boolean deleteUsers(String id){

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public int addProduct(String id, String productId , String merchantId){

        boolean checkProduct = false;

        int index =-1;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                index=i;
                break;
            }
        }

        // if id not found return ( id not found)
        if(index == -1){
            return -1;
        }

        int productIndex = 0;
        for (int i = 0; i < productService.getProduct().size(); i++) {
            if(productService.getProduct().get(i).getId().equals(productId)){
                checkProduct = true;
                productIndex = i;
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

        int inStock = 0 ;
        int inStockIndex = 0;
        for (int i = 0; i < merchantStockService.getMerchantStock().size(); i++) {
            if(merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)){
                inStock = merchantStockService.getMerchantStock().get(i).getStock();
                inStockIndex = i ;
                break;
            }
        }

        if(inStock == 0 ){
            // if there's no stock of this product = return out of stock
            return 4;
        }else{
            if(users.get(index).getBalance() < productService.getProduct().get(productIndex).getPrice()){
                // if balance < price = return you don't have much money
                return 5;
            }else{
                users.get(index).setBalance(users.get(index).getBalance()-productService.getProduct().get(productIndex).getPrice());
                merchantStockService.getMerchantStock().get(inStockIndex).setStock(merchantStockService.getMerchantStock().get(inStockIndex).getStock()-1);
            }
        }
        //users.get(index).setStock(merchantStocks.get(index).getStock()+stock);

        return 0;

    }

}
