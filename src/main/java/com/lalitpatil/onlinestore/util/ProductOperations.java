package com.lalitpatil.onlinestore.util;

import com.lalitpatil.onlinestore.exception.SellerNotFoundException;
import com.lalitpatil.onlinestore.model.Product;
import com.lalitpatil.onlinestore.model.ProductCategory;
import com.lalitpatil.onlinestore.model.Seller;
import com.lalitpatil.onlinestore.store.ProductCatalog;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductOperations {
    ProductCatalog productCatalog;
    SellerOperations sellerOperations;

    public ProductOperations(ProductCatalog productCatalog, SellerOperations sellerOperations) {
        this.productCatalog = productCatalog;
        this.sellerOperations = sellerOperations;
    }

    public Product getProductById(long id) {
        Product product = null;
        for(Product existingProduct : ProductCatalog.products) {
            if(existingProduct.getId() == id) {
                product = existingProduct;
                break;
            }
        }
        return product;
    }

    public Set<Product> getProducts() {
        return ProductCatalog.products;
    }

    public Set<Product> getProducts(ProductCategory category) {
        return ProductCatalog.products.stream().filter(product -> product.getCategory() == category).collect(Collectors.toSet());
    }

    public Product getProduct(long id) {
        Product product = null;
        for(Product existingProduct : ProductCatalog.products) {
            if(existingProduct.getId() == id) {
                product = existingProduct;
                break;
            }
        }
        return product;
    }

    public Long addProductToCatalog(Product product) {
        Seller seller = this.sellerOperations.getSellerById(product.getSellerId());
        if(seller != null) {
            productCatalog.addProduct(product);
            return product.getId();
        }
        else {
            throw new SellerNotFoundException(HttpStatus.NOT_FOUND, "Seller with id: "+product.getSellerId()+" not found");
        }
    }
}
