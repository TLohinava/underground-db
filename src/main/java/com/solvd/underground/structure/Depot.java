package com.solvd.underground.structure;

import com.solvd.underground.rollingstock.Train;

import java.util.List;

public class Depot {

    private Long id;
    private List<Train> trains;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }
}