package com.lalitpatil.onlinestore.store;

import com.lalitpatil.onlinestore.model.Cart;
import com.lalitpatil.onlinestore.model.User;
import com.lalitpatil.onlinestore.payment.CardPaymentProcessor;
import com.lalitpatil.onlinestore.payment.IPaymentProcessor;
import com.lalitpatil.onlinestore.payment.NetBankingPaymentProcessor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class UserStore {
    public static Set<User> users = new LinkedHashSet<>();

    //This code is added to test apis
    //Assumption is that system has users authenticated and ready
    public UserStore() {
        IPaymentProcessor alicePaymentProcessor = new CardPaymentProcessor();
        IPaymentProcessor bobPaymentProcessor = new NetBankingPaymentProcessor();
        this.addUser(new User("Alice", "Newman", new Cart(), alicePaymentProcessor));
        this.addUser(new User("Bob", "Oldman", new Cart(), bobPaymentProcessor));
    }
    public void addUser(User user) {
        this.users.add(user);
    }
}
