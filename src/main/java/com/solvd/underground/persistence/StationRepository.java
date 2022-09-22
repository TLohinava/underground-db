package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Station;

public interface StationRepository {

    void create(Station station);

    Station read(Long id);

    void update(Station station, Long id);

    void delete(Long id);

}
