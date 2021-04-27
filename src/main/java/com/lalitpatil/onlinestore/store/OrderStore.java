package com.lalitpatil.onlinestore.store;

import com.lalitpatil.onlinestore.model.Order;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class OrderStore {
    public static Set<Order> orders = new LinkedHashSet<>();

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
