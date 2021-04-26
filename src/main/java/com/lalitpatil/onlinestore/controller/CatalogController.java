package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.util.ProductOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {
    @Autowired
    ProductOperations productOperations;

    @RequestMapping(value = "/catalog/addProduct", method = RequestMethod.POST)
    public ResponseEntity addProductToCatalog(long sellerId, Product product) {
        this.productOperations.addProductToCatalog(sellerId, product);

        return ResponseEntity.ok("");
    }
}
