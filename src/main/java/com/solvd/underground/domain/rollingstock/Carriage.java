package com.solvd.underground.domain.rollingstock;

public class Carriage implements IAnnounce{

    private Long id;
    private Integer seatCapacity;
    private String manufacturer;
    private Integer number;

    @Override
    public void onEvent(EventType type) {
        switch(type) {
            case ARRIVAL:
                this.announce();
                this.doorsOpen();
                break;
            case DEPARTURE:
                this.doorsClose();
                break;
            default:
                break;
        }
    }

    public void doorsOpen() {
        System.out.println("Doors are opening.");
    }

    public void doorsClose() {
        System.out.println("Mind the closing doors.");
    }

    public void announce() {
        System.out.println("We have arrived to the station.");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}