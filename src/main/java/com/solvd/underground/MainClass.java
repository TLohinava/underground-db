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

        Station stationOne = new Station();
        stationOne.setName("Malinovka");
        Station stationTwo = new Station();
        stationTwo.setName("Kastrychnitskaia");

        Train train = new Train();
        train.setNumber(3214);
        train.setCarriages(List.of(carriage));

        Depot depot = new Depot();
        depot.setAddress("Fabriciusa, 24");
        depot.setTrains(List.of(train));

        Line line = new Line();
        line.setDepot(depot);
        line.setName("Moskovskaya");
        line.setStations(List.of(stationOne, stationTwo));

        LineService ls = new LineServiceImpl();
        ls.create(line);
        ls.getAll().stream()
                .flatMap(l -> l.getStations().stream())
                .forEach(st -> System.out.println(st.getName()));
    }
}