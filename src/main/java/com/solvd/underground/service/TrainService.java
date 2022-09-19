package com.solvd.underground.service;

import com.solvd.underground.domain.rollingstock.Train;

public interface TrainService {

    Train create(Train train, Long depotId);

    void delete(int id);

}