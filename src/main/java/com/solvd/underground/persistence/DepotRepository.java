package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Depot;

public interface DepotRepository extends BaseRepository<Depot> {

    Depot findDepot();

}