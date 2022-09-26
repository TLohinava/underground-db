package com.solvd.underground.domain.payment;

import com.solvd.underground.domain.payment.IPay;

public class BankCard implements IPay {

    @Override
    public void pay() {
        System.out.println("You have paid 90 cents, you can pass now.");
    }

}