package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.exception.PaymentFailedException;
import com.lalitpatil.onlinestore.exception.ProductNotFoundException;
import com.lalitpatil.onlinestore.exception.UserNotFoundException;
import com.lalitpatil.onlinestore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

public class CartOperations {
    UserOperations userOperations;
    ProductOperations productOperations;

    public CartOperations(UserOperations userOperations, ProductOperations productOperations) {
        this.userOperations = userOperations;
        this.productOperations = productOperations;
    }

    public void addProductToCart(long userId, long productId) {
        User user = this.userOperations.getUserById(userId);
        if(user != null) {
            Product product = this.productOperations.getProductById(productId);
            if(product != null && product.isAvailable()) {
                Cart userCart = user.getCart();
                userCart.addProduct(product);
                user.getCart().setEmpty(false);
            }
        }
    }

    public void removeProductFromCart(long userId, long productId) {
        User user = this.userOperations.getUserById(userId);
        if(user != null) {
            Product product = this.productOperations.getProductById(productId);
            if(product != null && user.getCart().getProducts().contains(product)) {
                    user.getCart().removeProduct(product);
            }
            else {
                throw new ProductNotFoundException();
            }
        }
        else {
            throw new UserNotFoundException();
        }
    }

    public Set<Product> getCartProductsForUser(long userId) {
        User user = this.userOperations.getUserById(userId);
        if(user != null) {
            Cart userCart = user.getCart();
            if(userCart != null) {
                return userCart.getProducts();
            }
        }
        else
            throw new UserNotFoundException();
        return Collections.EMPTY_SET;
    }
}
