package com.lalitpatil.onlinestore.payment;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.lalitpatil.onlinestore.model.User;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NetBankingPaymentProcessor implements IPaymentProcessor{
    @Override
    public boolean processPayment(User user, Double totalCost) {
        //process payment
        // if successful return true else false
        // for now testing purpose we will return true
        return true;
    }

}
