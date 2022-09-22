package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Train;

import java.util.Optional;

public interface TrainRepository extends BaseRepository<Train> {

    void create(Train train, Long depotId);

    void update(Train train, Long id, Long depotId);

    Optional<Train> findTrain();

}