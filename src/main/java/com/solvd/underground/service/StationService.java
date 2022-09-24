package com.solvd.underground.service;

import com.solvd.underground.domain.structure.Station;

public interface StationService {

    Station create(Station station);

    void delete(int id);

}
