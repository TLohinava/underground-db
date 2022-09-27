package com.solvd.underground.domain.payment;

public class Wallet {

    private final Token token;
    private final TravelCard travelCard;
    private final BankCard bankCard;

    public Wallet() {
        token = new Token();
        travelCard = new TravelCard();
        bankCard = new BankCard();
    }

    public void payWithToken() {
        token.pay();
    }

    public void payWithBankCard() {
        bankCard.pay();
    }

    public void payWithTravelCard() {
        travelCard.pay();
    }
}