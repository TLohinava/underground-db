package com.solvd.underground.service;

import com.solvd.underground.domain.structure.Depot;

public interface DepotService {

    Depot create(Depot depot);

    Depot findDepot();

    void delete(int id);

}