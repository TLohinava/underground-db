package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Depot;

import java.util.List;

public interface DepotRepository {

    void create(Depot depot);

    Depot read(Long id);

    List<Depot> readAll();

    void update(Depot depot, Long id);

    void delete(Long id);

}
