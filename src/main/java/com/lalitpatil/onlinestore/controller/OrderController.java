package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.exception.OrderNotFoundException;
import com.lalitpatil.onlinestore.model.Order;
import com.lalitpatil.onlinestore.util.OrderOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    OrderOperations orderOperations;

    public OrderController(OrderOperations orderOperations) {
        this.orderOperations = orderOperations;
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrderDetails(@PathVariable Long orderId) {
        Order order = null;
        try {
            order = this.orderOperations.getOrderById(orderId);
        }catch (OrderNotFoundException orderNotFoundException) {
            ResponseEntity
                    .status(orderNotFoundException.getStatus())
                    .body(orderNotFoundException.getReason());
        }
        return ResponseEntity.ok(order);
    }
}
