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
    @Setter boolean isAvailable;
    @Setter ProductCategory category;
    @Setter Seller seller;

    public Product(String name, String description, Double price, boolean isAvailable, ProductCategory category, Seller seller) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.category = category;
        this.seller = seller;
    }

    public Product(String name, String description, Double price, boolean isAvailable, ProductCategory category) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.category = category;
        this.seller = null;
    }
}
