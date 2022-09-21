package com.solvd.underground;

import com.solvd.underground.domain.employee.Driver;
import com.solvd.underground.domain.rollingstock.*;
import com.solvd.underground.domain.structure.*;
import com.solvd.underground.service.*;
import com.solvd.underground.service.impl.*;

import java.time.LocalDate;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.setFirstName("Anton");
        driver.setLastName("Barsky");
        driver.setDob(LocalDate.of(1996, 1, 1));

        Carriage carriage = new Carriage();
        carriage.setSeatCapacity(100);
        carriage.setManufacturer("Stadler");
        carriage.setNumber(6655);

        Station station = new Station();
        station.setName("Mogilevskaya");

        Train train = new Train();
        train.setNumber(5566);
        train.setCarriages(List.of(carriage));
        train.setDrivers(List.of(driver));

        Depot depot = new Depot();
        depot.setAddress("Moskovskaya, 5");
        depot.setTrains(List.of(train));

        Line line = new Line();
        line.setDepot(depot);
        line.setName("Autozavodskaya");
        line.setStations(List.of(station));

        LineService ls = new LineServiceImpl();
        ls.create(line);
    }
}