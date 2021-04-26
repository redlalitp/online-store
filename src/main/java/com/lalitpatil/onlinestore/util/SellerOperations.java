package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.model.Seller;
import com.lalitpatil.onlinestore.store.SellerStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellerOperations {
    @Autowired
    SellerStore sellerStore;

    public Seller getSellerById(long id) {
        Seller seller = null;
        for(Seller existingSeller : sellerStore.getSellers()) {
            if(seller.getId() == id) {
                return seller;
            }
        }
        return seller;
    }
}
