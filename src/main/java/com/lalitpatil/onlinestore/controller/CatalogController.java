package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.model.ProductCategory;
import com.lalitpatil.onlinestore.util.ProductOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

    ProductOperations productOperations;

    public CatalogController(ProductOperations productOperations) {
        this.productOperations = productOperations;
    }

    @RequestMapping(value = "/catalog/addProduct", method = RequestMethod.POST)
    public ResponseEntity addProductToCatalog(Long sellerId, String productName, String productDescription, Double price, Boolean isAvailable, ProductCategory productCategory) {
        Product product = new Product(productName, productDescription, price, isAvailable, productCategory);
        Long newProductId = this.productOperations.addProductToCatalog(sellerId, product);

        return ResponseEntity.ok("");
    }
}
