package com.solvd.underground;

import com.solvd.underground.domain.rollingstock.Carriage;
import com.solvd.underground.domain.rollingstock.Train;
import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.domain.structure.Line;
import com.solvd.underground.domain.structure.Station;
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
        station.setName("Mogilevskaya");

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

//        CarriageRepository cm = new CarriageMapperImpl();
//        cm.update(carriage, 20l);
//        DepotRepository dr = new DepotMapperImpl();
//        dr.update(depot, 18l);
//        dr.delete(10l);

//        LineRepository lr = new LineMapperImpl();
//        lr.create(line);

//        StationRepository sr = new StationMapperImpl();
//        sr.update(station,9l);

//        TrainRepository tr = new TrainMapperImpl();
//        System.out.println(tr.read(16l).getCarriages().size());
    }
}
