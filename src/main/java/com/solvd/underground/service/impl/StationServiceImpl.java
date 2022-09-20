package com.solvd.underground.service.impl;

import com.solvd.underground.domain.structure.Station;
import com.solvd.underground.persistence.StationRepository;
import com.solvd.underground.persistence.impl.StationRepositoryImpl;
import com.solvd.underground.service.StationService;

public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl() {
        this.stationRepository = new StationRepositoryImpl();
    }

    @Override
    public Station create(Station station) {
        station.setId(null);
        stationRepository.create(station);
        return station;
    }


    @Override
    public void delete(int id) {
        stationRepository.delete((long) id);
    }
}