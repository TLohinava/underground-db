package com.solvd.underground;

import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.domain.structure.Station;
import com.solvd.underground.persistence.*;
import com.solvd.underground.persistence.impl.*;

public class MainClass {

    public static void main(String[] args) {
//        CarriageRepository cm = new CarriageMapperImpl();

//        DepotRepository dr = new DepotMapperImpl();
//        dr.update(depot, 18l);
//        dr.delete(10l);

//        LineRepository lr = new LineMapperImpl();

        Station station = new Station();
        station.setName("Utttuu");
        StationRepository sr = new StationMapperImpl();
        sr.update(station,9l);

//        TrainRepository tr = new TrainMapperImpl();
    }
}
