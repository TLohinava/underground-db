package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Line;

import java.util.*;

public interface LineRepository extends BaseRepository<Line> {

        List<Line> findAll();

}