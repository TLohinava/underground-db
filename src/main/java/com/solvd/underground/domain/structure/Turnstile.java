package com.solvd.underground.domain.structure;

import com.solvd.underground.domain.payment.IPay;

public class Turnstile {

    private Long id;
    private IPay choiceOfPayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IPay getChoiceOfPayment() {
        return choiceOfPayment;
    }

    public void setChoiceOfPayment(IPay choiceOfPayment) {
        this.choiceOfPayment = choiceOfPayment;
    }
}