package com.solvd.underground.service;

import com.solvd.underground.domain.structure.Line;

import java.util.List;

public interface LineService {

    Line create(Line line, Long depotId);

    List<Line> getAll();

    void delete(int id);

}
