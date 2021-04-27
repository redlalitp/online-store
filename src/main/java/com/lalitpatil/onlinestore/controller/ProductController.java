package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.model.ProductCategory;
import com.lalitpatil.onlinestore.util.ProductOperations;
import com.lalitpatil.onlinestore.util.UserOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@RestController
public class ProductController {
    private UserOperations userOperations;
    private ProductOperations productOperations;

    public ProductController(UserOperations userOperations, ProductOperations productOperations) {
        this.userOperations = userOperations;
        this.productOperations = productOperations;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity getProducts(@RequestParam ProductCategory category) {
        Set<Product> products;
        if(category != null) {
            products = this.productOperations.getProducts(category);
        }
        else {
            products = this.productOperations.getProducts();
        }
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity getProduct(@RequestParam long id) {
        Product product = this.productOperations.getProduct(id);
        if(product == null) {
            throw new NullPointerException();
        }
        return ResponseEntity.ok(product);
    }
}
