package com.solvd.underground.rollingstock;

import com.solvd.underground.employee.Driver;

import java.util.List;

public class Train {

    private Long id;
    private List<Driver> drivers;
    private List<Carriage> carriages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }
}