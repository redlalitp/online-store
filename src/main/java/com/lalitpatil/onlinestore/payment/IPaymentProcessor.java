package com.lalitpatil.onlinestore.payment;

import com.lalitpatil.onlinestore.model.User;

import java.util.Set;

public interface IPaymentProcessor {
    boolean processPayment(User user, Double cost);
}
