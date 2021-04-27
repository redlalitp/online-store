package com.lalitpatil.onlinestore.controller;

import com.lalitpatil.onlinestore.exception.SellerNotFoundException;
import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.util.ProductOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CatalogController {

    ProductOperations productOperations;

    public CatalogController(ProductOperations productOperations) {
        this.productOperations = productOperations;
    }

    @RequestMapping(value = "/catalog/addProduct", method = RequestMethod.POST)
    public ResponseEntity addProductToCatalog(@RequestBody Product product) {
        Long newProductId;
            try {
                newProductId = this.productOperations.addProductToCatalog(product);
            }catch (SellerNotFoundException sellerNotFoundException) {
                return ResponseEntity
                        .status(sellerNotFoundException.getStatus())
                        .body(sellerNotFoundException.getReason());
            }catch (NullPointerException e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Bad request, required parameters are not present");
            }
        return ResponseEntity.ok(newProductId);
    }
}
