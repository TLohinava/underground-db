package com.solvd.underground.service;

import com.solvd.underground.domain.structure.Station;

public interface StationService {

    Station create(Station station);

    Station read(Long id);

    void update(Station station, Long id);

    void delete(Long id);

}
