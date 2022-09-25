package com.solvd.underground;

import com.solvd.underground.domain.rollingstock.*;
import com.solvd.underground.domain.structure.*;
import com.solvd.underground.service.*;
import com.solvd.underground.service.impl.*;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Carriage carriage = new Carriage();
        carriage.setSeatCapacity(100);
        carriage.setManufacturer("Stadler");
        carriage.setNumber(7896);

        Station station = new Station();
        station.setName("Mogilevskaya");

        Train train = new Train();
        train.setNumber(3214);
        train.setCarriages(List.of(carriage));

        Depot depot = new Depot();
        depot.setAddress("Nemiga, 5");
        depot.setTrains(List.of(train));

        Line line = new Line();
        line.setDepot(depot);
        line.setName("Zelenaluzhskaya");
        line.setStations(List.of(station));

        CarriageService cs = new CarriageServiceImpl();

        DepotService ds = new DepotServiceImpl();

        LineService ls = new LineServiceImpl();

        StationService ss = new StationServiceImpl();
    }
}