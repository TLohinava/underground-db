package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Line;

import java.util.List;

public interface LineRepository {

    void create(Line line);

    Line read(Long id);

    List<Line> readAll();

    void update(Line line, Long id);

    void delete(Long id);

}
