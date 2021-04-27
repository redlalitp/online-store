package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.exception.ProductNotFoundException;
import com.lalitpatil.onlinestore.exception.UserNotFoundException;
import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.util.CartOperations;
import com.lalitpatil.onlinestore.util.UserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RestController
public class CartController {
    CartOperations cartOperations;
    UserOperations userOperations;

    public CartController(CartOperations cartOperations, UserOperations userOperations) {
        this.cartOperations = cartOperations;
        this.userOperations = userOperations;
    }

    @RequestMapping(value = "/cart/user/{userId}/addProduct/{productId}", method = RequestMethod.POST)
    public ResponseEntity addProductToCart(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            this.cartOperations.addProductToCart(userId, productId);
        }catch (ProductNotFoundException productNotFoundException) {
            return ResponseEntity
                    .status(productNotFoundException.getStatus())
                    .body(productNotFoundException.getReason());
        }catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity
                    .status(userNotFoundException.getStatus())
                    .body(userNotFoundException.getReason());
        }
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/cart/user/{userId}/removeProduct/{productId}", method = RequestMethod.POST)
    public ResponseEntity removeProductFromCart(@PathVariable Long userId,@PathVariable Long productId) {
        try {
            this.cartOperations.removeProductFromCart(userId, productId);
        }catch (ProductNotFoundException productNotFoundException) {
            return ResponseEntity
                    .status(productNotFoundException.getStatus())
                    .body(productNotFoundException.getReason());
        }catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity
                    .status(userNotFoundException.getStatus())
                    .body(userNotFoundException.getReason());
        }
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/cart/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity getCartProducts(@PathVariable Long userId) {
        Set<Product> products;
        try {
            products = this.cartOperations.getCartProductsForUser(userId);
        }catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity
                    .status(userNotFoundException.getStatus())
                    .body(userNotFoundException.getReason());
        }
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/cart/user/{userId}/checkout", method = RequestMethod.POST)
    public ResponseEntity checkout(@PathVariable Long userId) {
        Long orderId = -1L;
        try {
            orderId = this.userOperations.checkout(userId);
        }catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity
                    .status(userNotFoundException.getStatus())
                    .body(userNotFoundException.getReason());
        }catch (RuntimeException runtimeException) {
            ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong please retry checkout");
        }
        return ResponseEntity.ok(orderId);
    }


}
