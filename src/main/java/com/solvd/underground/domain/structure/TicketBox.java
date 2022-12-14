package com.solvd.underground.domain.structure;

import com.solvd.underground.domain.employee.Cashier;

import java.util.List;

public class TicketBox {

    private Long id;
    private List<Cashier> cashiers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }
}