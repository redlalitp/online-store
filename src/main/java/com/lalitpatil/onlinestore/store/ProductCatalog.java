package com.lalitpatil.onlinestore.store;

import com.lalitpatil.onlinestore.model.Product;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class ProductCatalog {
    public static Set<Product> products = new LinkedHashSet<>();

//    ProductCatalog() {
//        products.add(new Product("A", "some Description", 24.5, true, ProductCategory.Food));
//        products.add(new Product("B", "some Description", 34.5, true, ProductCategory.clothing));
//        products.add(new Product("C", "some Description", 12.0, true, ProductCategory.Food));
//        products.add(new Product("D", "some Description", 3.0, true, ProductCategory.kitchen));
//        products.add(new Product("E", "some Description", 29.5, true, ProductCategory.clothing));
//    }

    public boolean addProduct(@NonNull Product product) {
        return this.products.add(product);
    }

    public boolean removeProduct(@NonNull Product product) {
        return this.products.remove(product);
    }
}
