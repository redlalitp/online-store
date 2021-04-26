package com.lalitpatil.onlinestore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class Seller {
    private static AtomicInteger count = new AtomicInteger(0);
    long id;
    @Setter String name;

    Seller(String name)
    {
        this.id = count.incrementAndGet();
        this.name = name;
    }
}
