package com.lalitpatil.onlinestore.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Cart {
    private static AtomicInteger count = new AtomicInteger(0);
    long id;
    @Setter boolean isEmpty;
    @Setter Set<Product> products;

    public Cart(boolean isEmpty, Set<Product> products) {
        this.id = count.incrementAndGet();
        this.isEmpty = isEmpty;
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
