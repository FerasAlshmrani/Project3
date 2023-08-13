package com.example.project3.Service;

import com.example.project3.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {


    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public boolean addCategory(Category category){

        // Must be UNIQUE ID
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(category.getId())){
                return false;
            }
        }

        categories.add(category);
        return true;
    }

    public int updateCategory(Category category,String id){

        int index = -1;
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(id)){
                index = i;
                break;
            }
        }

        if(index == -1){
            return -1;
        }
        // chack if there another categoryId because its must be UNIQUE ID
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(category.getId())){
                // if the update are the same id continue but if he finds another one return new ID must be UNIQUE
                if(categories.get(index).getId().equals(category.getId())){
                    continue;
                }else{
                    return 1;
                }
            }
        }
        categories.set(index,category);
        return 0;
    }

    public boolean deleteCategory(String id){

        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(id)){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }
}
