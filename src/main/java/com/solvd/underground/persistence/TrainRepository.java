package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Train;

import org.apache.ibatis.annotations.Param;

public interface TrainRepository extends BaseRepository<Train> {

    void create(@Param("train") Train train, @Param("depotId") Long depotId);

    void update(@Param("train") Train train, @Param("id") Long id, @Param("depotId") Long depotId);

}
