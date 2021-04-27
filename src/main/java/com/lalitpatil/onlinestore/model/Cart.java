package com.lalitpatil.onlinestore.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Cart {
    private static AtomicInteger count = new AtomicInteger(0);
    long id;
    @Setter boolean isEmpty;
    @Setter Set<Product> products;

    public Cart() {
        this.id = count.incrementAndGet();
        this.isEmpty = true;
        this.products = new LinkedHashSet<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public Double getCartTotal() {
        Double sum = 0.0;
        for(Product product : products) {
            sum = sum + product.getPrice();
        }
        return sum;
    }
}
