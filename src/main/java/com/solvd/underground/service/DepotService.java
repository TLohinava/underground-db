package com.solvd.underground.service;

import com.solvd.underground.domain.structure.Depot;

public interface DepotService {

    Depot create(Depot depot);

    Depot findDepot(Long id);

    void update(Depot depot, Long id);

    void delete(int id);

}