package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Train;

public interface TrainRepository extends BaseRepository<Train> {

    void create(Train train, Long depotId);

    void update(Train train, Long id, Long depotId);

}