package com.lalitpatil.onlinestore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class User {
    private static AtomicInteger count = new AtomicInteger(0);
    long id;
    @Setter String firstName;
    @Setter String lastName;
    Cart cart;

    User(String firstName, String lastName, Cart cart)
    {
        this.id = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = cart;
    }
}
