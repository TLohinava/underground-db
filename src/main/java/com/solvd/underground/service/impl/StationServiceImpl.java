package com.solvd.underground.service.impl;

import com.solvd.underground.domain.exception.QueryException;
import com.solvd.underground.domain.structure.Station;
import com.solvd.underground.persistence.StationRepository;
import com.solvd.underground.persistence.impl.mybatis.StationMapperImpl;
import com.solvd.underground.service.StationService;

public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl() {
//        this.stationRepository = new StationRepositoryImpl();
        this.stationRepository = new StationMapperImpl();
    }

    @Override
    public Station create(Station station) {
        station.setId(null);
        stationRepository.create(station);
        return station;
    }

    @Override
    public Station read(Long id) {
        return stationRepository.read(id)
                .orElseThrow(() -> new QueryException("No line found."));
    }

    @Override
    public void update(Station station, Long id) {
        stationRepository.update(station, id);
    }

    @Override
    public void delete(int id) {
        stationRepository.delete((long) id);
    }
}