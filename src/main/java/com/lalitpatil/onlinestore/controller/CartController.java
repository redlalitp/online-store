package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.util.CartOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CartController {
    @Autowired
    CartOperations cartOperations;

    @RequestMapping(value = "/cart/addProduct", method = RequestMethod.POST)
    public ResponseEntity addProductToCart(@RequestParam long userId,@RequestParam long productId) {
        this.cartOperations.addProductToCart(userId, productId);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/cart/removeProduct", method = RequestMethod.POST)
    public ResponseEntity removeProductFromCart(@RequestParam long userId,@RequestParam long productId) {
        this.cartOperations.removeProductFromCart(userId, productId);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ResponseEntity getCartProducts(@RequestParam long userId) {
        Set<Product> products = this.cartOperations.getCartProductsForUser(userId);
        return ResponseEntity.ok(products);
    }
}
