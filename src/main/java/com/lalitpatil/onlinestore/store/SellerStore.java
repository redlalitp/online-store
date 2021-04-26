package com.lalitpatil.onlinestore.store;

import com.lalitpatil.onlinestore.model.Seller;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class SellerStore {
    @Getter
    Set<Seller> sellers = new LinkedHashSet<>();
}
