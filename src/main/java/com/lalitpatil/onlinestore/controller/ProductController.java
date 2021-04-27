package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.model.ProductCategory;
import com.lalitpatil.onlinestore.util.ProductOperations;
import com.lalitpatil.onlinestore.util.UserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getProducts(@RequestParam(required = false) ProductCategory category) {
        Set<Product> products;
        if(category != null) {
            products = this.productOperations.getProducts(category);
        }
        else {
            products = this.productOperations.getProducts();
        }
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity getProduct(@PathVariable Long productId) {
        Product product = this.productOperations.getProduct(productId);
        if(product == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("product not found");
        }
        return ResponseEntity.ok(product);
    }
}
