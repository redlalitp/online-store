package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.exception.SellerNotFoundException;
import com.lalitpatil.onlinestore.model.Seller;
import com.lalitpatil.onlinestore.store.SellerStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellerOperations {

    public Seller getSellerById(long id) {
        for(Seller seller : SellerStore.sellers) {
            if(seller.getId() == id) {
                return seller;
            }
        }
        throw new SellerNotFoundException();
    }
}
