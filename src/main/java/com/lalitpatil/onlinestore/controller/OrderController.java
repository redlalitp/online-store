package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.model.Order;
import com.lalitpatil.onlinestore.util.OrderOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    OrderOperations orderOperations;

    public OrderController(OrderOperations orderOperations) {
        this.orderOperations = orderOperations;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity getOrderDetails(@RequestParam long orderId) {
        Order order = this.orderOperations.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
