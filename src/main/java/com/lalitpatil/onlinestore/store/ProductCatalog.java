package com.lalitpatil.onlinestore.store;

import com.lalitpatil.onlinestore.model.Product;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class ProductCatalog {
    public static Set<Product> products = new LinkedHashSet<>();

    public boolean addProduct(@NonNull Product product) {
        return this.products.add(product);
    }

    public boolean removeProduct(@NonNull Product product) {
        return this.products.remove(product);
    }
}
