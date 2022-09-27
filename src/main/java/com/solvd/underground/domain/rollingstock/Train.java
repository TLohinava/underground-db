package com.solvd.underground.domain.rollingstock;

import com.solvd.underground.domain.employee.Driver;

import java.util.List;

public class Train implements IAnnounce {

    private Long id;
    private Integer number;
    private List<Driver> drivers;
    private List<Carriage> carriages;

    @Override
    public void onEvent(EventType type) {
        carriages.forEach(c -> c.onEvent(type));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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