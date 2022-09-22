package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Train;

import java.util.List;

public interface TrainRepository {

    void create(Train train);

    Train read(Long id);

    List<Train> readAll();

    void update(Train train, Long id);

    void delete(Long id);

}
