package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Line;

import java.util.List;

public interface LineRepository extends BaseRepository<Line> {

    void create(Line line, Long depotId);

    List<Line> findAll();

}