package com.solvd.underground.service;

import com.solvd.underground.domain.structure.Line;
import com.solvd.underground.domain.structure.Station;

import java.util.List;

public interface LineService {

    Line create(Line line);

    List<Line> getAll();

    Line read(Long id);

    void update(Line line, Long id);

    void delete(Long id);

}