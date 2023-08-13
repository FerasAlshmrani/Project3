package com.example.project3.Service;

import com.example.project3.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ProductService {


    ArrayList<Product> products = new ArrayList<>();

    private final CategoryService categoryService;
    public ArrayList<Product> getProduct(){
        return products;
    }

    public int addProduct(Product product){

        // Must be UNIQUE ID
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(product.getId())){
                return 2;
            }
        }

        for (int i = 0; i < categoryService.getCategories().size(); i++) {
            if(categoryService.getCategories().get(i).getId().equals(product.getCategoryId())){
                products.add(product);
                return 0;
            }
        }



        return 1;

    }

    public int updateProduct(Product product,String id){

        int index=-1 ;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(id)){
                index = i;
                break;
            }
        }
        if(index == -1){
            return -1;
        }

        // chack if there are another categoryId because its must be UNIQUE ID
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(product.getId())){
                // if the update are the same id continue but if he finds another one return new ID must be UNIQUE
                if(products.get(index).getId().equals(product.getId())){
                    continue;
                }else{
                    return 2;
                }
            }
        }

        for (int i = 0; i < categoryService.getCategories().size(); i++) {
            if (categoryService.getCategories().get(i).getId().equals(product.getCategoryId())) {
                products.set(index,product);
                return 0;
            }
        }
        return 1;



    }

    public boolean deleteProduct(String id){

        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }
// method got product id
}
