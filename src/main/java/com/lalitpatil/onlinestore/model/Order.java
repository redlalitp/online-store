package com.lalitpatil.onlinestore.model;

import com.lalitpatil.onlinestore.payment.IPaymentProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class Order {
    private static AtomicInteger count = new AtomicInteger(0);
    long id;
    @Setter Set<Product> products;
    @Setter Double totalCost;
    @Setter OrderStatus status;
    @Setter IPaymentProcessor paymentMethod;
    @Setter PaymentStatus paymentStatus;
    Date dateCreated;

    public Order(Set<Product> products, Double totalCost, OrderStatus status, IPaymentProcessor paymentMethod, PaymentStatus paymentStatus) {
        this.products = products;
        this.totalCost = totalCost;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.dateCreated = new Date();
    }
}
