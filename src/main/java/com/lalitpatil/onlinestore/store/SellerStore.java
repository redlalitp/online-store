package com.lalitpatil.onlinestore.store;

import com.lalitpatil.onlinestore.model.Seller;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class SellerStore {

    public static Set<Seller> sellers = new LinkedHashSet<>();

    //This code is added to test apis
    //Assumption is that system has sellers authenticated and ready
    public SellerStore() {
        this.addSeller(new Seller("seller1"));
        this.addSeller(new Seller("seller2"));
        this.addSeller(new Seller("seller3"));
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
    }
}
