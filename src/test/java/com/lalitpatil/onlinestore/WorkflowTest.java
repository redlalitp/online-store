package com.lalitpatil.onlinestore;

import com.lalitpatil.onlinestore.controller.CartController;
import com.lalitpatil.onlinestore.controller.CatalogController;
import com.lalitpatil.onlinestore.controller.OrderController;
import com.lalitpatil.onlinestore.controller.ProductController;
import com.lalitpatil.onlinestore.model.*;
import com.lalitpatil.onlinestore.payment.CardPaymentProcessor;
import com.lalitpatil.onlinestore.payment.IPaymentProcessor;
import com.lalitpatil.onlinestore.payment.NetBankingPaymentProcessor;
import com.lalitpatil.onlinestore.store.ProductCatalog;
import com.lalitpatil.onlinestore.store.SellerStore;
import com.lalitpatil.onlinestore.store.UserStore;
import com.lalitpatil.onlinestore.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkflowTest {
    CartController cartController;
    CatalogController catalogController;
    OrderController orderController;
    ProductController productController;
    UserStore userStore = new UserStore();
    SellerStore sellerStore = new SellerStore();
    ProductCatalog productCatalog = new ProductCatalog();
    IPaymentProcessor netBankingPaymentProcessor = new NetBankingPaymentProcessor();
    IPaymentProcessor cardPaymentProcessor = new CardPaymentProcessor();

    @BeforeEach
    void setup() {
        SellerOperations sellerOperations = new SellerOperations();
        ProductOperations productOperations = new ProductOperations(productCatalog, sellerOperations);
        UserOperations userOperations = new UserOperations();
        CartOperations cartOperations = new CartOperations(userOperations, productOperations);
        OrderOperations orderOperations = new OrderOperations();
        cartController = new CartController(cartOperations, userOperations);
        catalogController = new CatalogController(productOperations);
        orderController = new OrderController(orderOperations);
        productController = new ProductController(userOperations, productOperations);
    }

    @Test
    void SunnyDayScenariosTest() {
        Cart user1Cart = new Cart();
        Cart user2Cart = new Cart();
        User user1 = new User("John","Doe",user1Cart,netBankingPaymentProcessor);
        User user2 = new User("Alice","Crow",user2Cart,cardPaymentProcessor);
        Seller seller1 = new Seller("seller1");
        Seller seller2 = new Seller("seller2");
        sellerStore.addSeller(seller1);
        sellerStore.addSeller(seller2);
        userStore.addUser(user1);
        userStore.addUser(user2);
        // Seller adds products to catalog
        Product product1 = new Product("product1", "Delicious food", 30.0, true, ProductCategory.Food ,seller1.getId());
        Product product3 = new Product("product3", "Long lasting", 799.0, true, ProductCategory.kitchen, seller1.getId());
        Product product5 = new Product("product5", "Very delicious food!", 200.0, true, ProductCategory.Food, seller1.getId());
        Product product2 = new Product("product2", "Comfortable, contemporary, in your budget", 1200.0, true, ProductCategory.clothing, seller2.getId());
        Product product4 = new Product("product4", "The best!", 20000.0, true, ProductCategory.electronics, seller2.getId());
        catalogController.addProductToCatalog(product1);
        catalogController.addProductToCatalog(product3);
        catalogController.addProductToCatalog(product5);
        catalogController.addProductToCatalog(product2);
        catalogController.addProductToCatalog(product4);

        //Check all added products
        System.out.println("All Products in catalog");
        System.out.println(productController.getProducts(null).getBody());
        //Get all products in category food
        System.out.println("\nAll Products in catalog in category food");
        System.out.println(productController.getProducts(ProductCategory.Food).getBody());

        //Add Product to cart
        assertEquals(true,user1.getCart().isEmpty());
        cartController.addProductToCart(user1.getId(), 3L);
        cartController.addProductToCart(user1.getId(), 5L);
        assertEquals(false,user1.getCart().isEmpty());
        assertEquals(true,user2.getCart().isEmpty());

        //Get all products added in cart for user 1
        System.out.println("\n\nAll Products in cart for user1");
        System.out.println(cartController.getCartProducts(user1.getId()).getBody());

        //Checkout
        System.out.println("\n\nCheckout the items added in cart");
        Long orderId = (Long)cartController.checkout(user1.getId()).getBody();
        System.out.println(orderController.getOrderDetails(orderId).getBody());
    }

    @Test
    void RainyDayScenariosTest() {
        Cart user1Cart = new Cart();
        Cart user2Cart = new Cart();
        User user1 = new User("John","Doe",user1Cart,null);
        //User user2 = new User("Alice","Crow",user2Cart,cardPaymentProcessor);
        Seller seller1 = new Seller("seller1");
        Seller seller2 = new Seller("seller2");
        sellerStore.addSeller(seller1);
        sellerStore.addSeller(seller2);
        userStore.addUser(user1);

        //if seller id is not provided, should fail with bad request
        Product product1 = new Product("product1", "Delicious food", 30.0, true, ProductCategory.Food ,null);
        assertEquals(HttpStatus.BAD_REQUEST, catalogController.addProductToCatalog(product1).getStatusCode());


        //seller doesnt exists
        Product product2 = new Product("product1", "Delicious food", 30.0, true, ProductCategory.Food ,9L);
        assertEquals(HttpStatus.NOT_FOUND, catalogController.addProductToCatalog(product2).getStatusCode());

        //Add Product to cart
        //assertEquals(true,user1.getCart().isEmpty());
        cartController.addProductToCart(user1.getId(), 2L);
        cartController.addProductToCart(user1.getId(), 3L);
        //assertEquals(user1.getCart().isEmpty(), false);
        //assertEquals(user2.getCart().isEmpty(), true);

        //Get all products added in cart for user 1
        System.out.println("\n\nAll Products in cart for user1");
        System.out.println(cartController.getCartProducts(user1.getId()).getBody());

        //Checkout
        System.out.println("\n\nCheckout the items added in cart");
        //Long orderId = (Long)cartController.checkout(user1.getId()).getBody();
        //System.out.println(orderController.getOrderDetails(orderId).getBody());
    }
}
