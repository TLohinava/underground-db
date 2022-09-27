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
                this.doorsOpen();
                this.announce(type);
                break;
            case DEPARTURE:
                this.doorsClose();
                this.announce(type);
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

    public void announce(EventType type) {
        switch(type) {
            case ARRIVAL:
                System.out.println("We have arrived to the station.");
                break;
            case DEPARTURE:
                System.out.println("We are leaving the station.");
                break;
            default:
                break;
        }
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