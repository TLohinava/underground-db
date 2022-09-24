package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Depot;

import org.apache.ibatis.annotations.Param;

public interface DepotRepository extends BaseRepository<Depot> {

    void update(@Param("depot") Depot depot, @Param("id") Long id);

}