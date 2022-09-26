package com.solvd.underground.domain.structure;

import com.solvd.underground.domain.rollingstock.Train;

import java.util.List;

public class Depot {

    private Long id;
    private String address;
    private List<Train> trains;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }
}