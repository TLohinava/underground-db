package com.solvd.underground.structure;

import com.solvd.underground.employee.Cashier;

import java.util.List;

public class TicketBox {

    private long id;
    private List<Cashier> cashiers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }
}