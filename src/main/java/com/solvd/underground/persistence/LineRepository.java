package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Line;

import com.solvd.underground.domain.structure.Station;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface LineRepository extends BaseRepository<Line> {

    void createStationConnection(@Param("line") Line line, @Param("station") Station station);

    List<Line> readAll();

    void update(@Param("line") Line line, @Param("id") Long id);

}