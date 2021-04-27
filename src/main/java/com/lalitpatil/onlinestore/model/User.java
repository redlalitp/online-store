package com.lalitpatil.onlinestore.model;

import com.lalitpatil.onlinestore.payment.IPaymentProcessor;
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
    IPaymentProcessor preferredPaymentMethod;

    public User(String firstName, String lastName, Cart cart, IPaymentProcessor preferredPaymentMethod)
    {
        this.id = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cart = cart;
        this.preferredPaymentMethod = preferredPaymentMethod;
    }
}
