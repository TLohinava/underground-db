package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Depot;

public interface DepotRepository {

    void create(Depot depot);

    Depot read(Long id);

    void update(Depot depot, Long id);

    void delete(Long id);

}
