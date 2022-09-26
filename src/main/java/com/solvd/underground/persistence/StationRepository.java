package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Station;

import org.apache.ibatis.annotations.Param;

public interface StationRepository extends BaseRepository<Station> {

    void update(@Param("station") Station station, @Param("id") Long id);

}