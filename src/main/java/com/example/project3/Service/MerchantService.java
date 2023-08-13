package com.example.project3.Service;

import com.example.project3.Model.Category;
import com.example.project3.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {



    ArrayList<Merchant> merchants = new ArrayList<>();


    public ArrayList<Merchant> getMerchants(){
        return merchants;
    }

    public boolean addMerchant(Merchant merchant){

        // Must be UNIQUE ID
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(merchant.getId())){
                return false;
            }
        }
        merchants.add(merchant);
        return true;
    }

    public int updateMerchant(Merchant merchant,String id){
        int index = -1;

        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(id)){
                index = i;
                break;
            }
        }
        if(index == -1){
            return -1;
        }

        // chack if there are another categoryId because its must be UNIQUE ID
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(merchant.getId())){
                // if the update are the same id continue but if he finds another one return new ID must be UNIQUE
                if(merchants.get(index).getId().equals(merchant.getId())){
                    continue;
                }else{
                    return 1;
                }
            }
        }
        merchants.set(index,merchant);
        return 0;

    }

    public boolean deleteMerchant(String id){

        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equals(id)){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }
}
