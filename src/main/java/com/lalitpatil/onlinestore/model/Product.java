package com.lalitpatil.onlinestore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;



@Getter
@ToString
public class Product {
    private static AtomicInteger count = new AtomicInteger(0);
    long id;
    @Setter String name;
    @Setter String description;
    @Setter Double price;
    @Setter boolean available;
    @Setter ProductCategory category;
    @Setter Long sellerId;

    public Product() {
        this.id = count.incrementAndGet();
    }

    public Product(String name, String description, Double price, boolean available, ProductCategory category, Long sellerId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.category = category;
        this.sellerId = sellerId;
    }
}
