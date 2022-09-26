package com.solvd.underground.domain.payment;

import com.solvd.underground.domain.payment.IPay;

public class Token implements IPay {

    @Override
    public void pay() {
        System.out.println("You token has been taken, you can pass now.");
    }

}