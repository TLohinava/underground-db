package com.solvd.underground.service;

import com.solvd.underground.domain.rollingstock.Train;

public interface TrainService {

    Train create(Train train, Long depotId);

    Train findTrain(Long id);

    void update(Train train, Long id, Long depotId);

    void delete(Long id);

}