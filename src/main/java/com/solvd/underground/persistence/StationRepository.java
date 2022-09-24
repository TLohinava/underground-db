package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Station;
import org.apache.ibatis.annotations.Param;

public interface StationRepository {

    void create(Station station);

    Station read(Long id);

    void update(@Param("station") Station station, @Param("id") Long id);

    void delete(Long id);

}
