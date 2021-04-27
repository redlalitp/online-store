package com.lalitpatil.onlinestore.payment;

import com.lalitpatil.onlinestore.model.User;

public class NetBankingPaymentProcessor implements IPaymentProcessor{
    @Override
    public boolean processPayment(User user, Double totalCost) {
        //process payment
        // if successful return true else false
        // for now testing purpose we will return true
        return true;
    }
}
