package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.exception.ProductNotFoundException;
import com.lalitpatil.onlinestore.exception.UserNotFoundException;
import com.lalitpatil.onlinestore.model.Cart;
import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class CartOperations {
    @Autowired
    UserOperations userOperations;
    @Autowired
    ProductOperations productOperations;

    public void addProductToCart(long userId, long productId) {
        User user = this.userOperations.getUserById(userId);
        if(user != null) {
            Product product = this.productOperations.getProductById(productId);
            if(product != null && product.isAvailable()) {
                user.getCart().addProduct(product);
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
        return Collections.EMPTY_SET;
    }
}