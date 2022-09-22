package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Station;

import java.util.List;

public interface StationRepository {

    void create(Station station);

    Station read(Long id);

    List<Station> readAll();

    void update(Station station, Long id);

    void delete(Long id);

}
