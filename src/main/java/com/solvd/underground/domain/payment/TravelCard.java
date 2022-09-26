package com.solvd.underground.domain.payment;

import com.solvd.underground.domain.payment.IPay;

public class TravelCard implements IPay {

    @Override
    public void pay() {
        System.out.println("You have an unlimited amount of rides, you can pass now.");
    }

}