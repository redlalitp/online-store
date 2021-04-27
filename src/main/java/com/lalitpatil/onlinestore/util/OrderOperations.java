package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.exception.OrderNotFoundException;
import com.lalitpatil.onlinestore.model.Order;
import com.lalitpatil.onlinestore.store.OrderStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderOperations {
    public Order getOrderById(long id) {
        for(Order order : OrderStore.orders) {
            if(order.getId() == id) {
                return order;
            }
        }
        throw new OrderNotFoundException();
    }
}
