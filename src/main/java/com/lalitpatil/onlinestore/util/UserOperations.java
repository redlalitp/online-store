package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.exception.PaymentFailedException;
import com.lalitpatil.onlinestore.exception.UserNotFoundException;
import com.lalitpatil.onlinestore.model.*;
import com.lalitpatil.onlinestore.payment.IPaymentProcessor;
import com.lalitpatil.onlinestore.store.OrderStore;
import com.lalitpatil.onlinestore.store.UserStore;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserOperations {

    public User getUserById(long id) {
        for(User existingUser : UserStore.users) {
            if(existingUser.getId() == id) {
                return existingUser;
            }
        }
        throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User with id: "+id+" not found");
    }

    public Long checkout(long userId) {
        User user = this.getUserById(userId);
        if(user != null) {
            Cart userCart = user.getCart();
            if(userCart != null) {
                if(!userCart.isEmpty()) {
                    IPaymentProcessor paymentProcessor = user.getPreferredPaymentMethod();
                    boolean success = paymentProcessor.processPayment(user, userCart.getCartTotal());
                    if(success) {
                        Order newOrder = new Order(userCart.getProducts(), userCart.getCartTotal(), OrderStatus.received,user.getPreferredPaymentMethod(),PaymentStatus.success);
                        OrderStore.orders.add(newOrder);
                        return newOrder.getId();
                    }
                    else {
                        Order newOrder = new Order(userCart.getProducts(), userCart.getCartTotal(),OrderStatus.cancelled,user.getPreferredPaymentMethod(),PaymentStatus.failure);
                        OrderStore.orders.add(newOrder);
                        throw new PaymentFailedException();
                    }
                }
            }
            else{
                throw new RuntimeException();
            }
        }
        else {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User with id: "+userId+" not found");
        }
        return -1L;
    }
}
