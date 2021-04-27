package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.exception.ProductNotFoundException;
import com.lalitpatil.onlinestore.exception.UserNotFoundException;
import com.lalitpatil.onlinestore.model.Cart;
import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
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
            else {
                throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product not found or is not available");
            }
        }
        else {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User with id: "+userId+" not found");
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
                throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "product not found");
            }
        }
        else {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User with id: "+userId+" not found");
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
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User with id: "+userId+" not found");
        return Collections.EMPTY_SET;
    }
}
