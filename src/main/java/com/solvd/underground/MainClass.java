package com.solvd.underground;

import com.solvd.underground.domain.rollingstock.*;
import com.solvd.underground.domain.structure.*;
import com.solvd.underground.persistence.*;
import com.solvd.underground.persistence.impl.*;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Carriage carriage = new Carriage();
        carriage.setSeatCapacity(10);
        carriage.setManufacturer("Stadler");
        carriage.setNumber(1111);

        Station station = new Station();
        station.setName("KGorka");

        Train train = new Train();
        train.setNumber(1066);
        train.setCarriages(List.of(carriage));

        Depot depot = new Depot();
        depot.setAddress("Moskovskaya, 5");
        depot.setTrains(List.of(train));

        Line line = new Line();
        line.setDepot(depot);
        line.setName("Autozavodskaya");
        line.setStations(List.of(station));

        CarriageRepository cm = new CarriageMapperImpl();

        DepotRepository dr = new DepotMapperImpl();

        LineRepository lr = new LineMapperImpl();
        lr.readAll().forEach(l -> System.out.println(l.getName()));

        StationRepository sr = new StationMapperImpl();

        TrainRepository tr = new TrainMapperImpl();
    }
}
