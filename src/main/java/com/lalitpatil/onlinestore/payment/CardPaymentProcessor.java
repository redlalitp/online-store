package com.lalitpatil.onlinestore.payment;

import com.lalitpatil.onlinestore.model.User;

public class CardPaymentProcessor implements IPaymentProcessor{
    @Override
    public boolean processPayment(User user, Double cost) {
        //process payment
        // if successful return true else false
        // for now testing purpose we will return true
        return true;
    }
}
