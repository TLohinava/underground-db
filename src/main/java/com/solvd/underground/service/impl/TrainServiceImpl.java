package com.solvd.underground.service.impl;

import com.solvd.underground.domain.exception.QueryException;
import com.solvd.underground.domain.rollingstock.*;
import com.solvd.underground.persistence.TrainRepository;
import com.solvd.underground.persistence.impl.TrainRepositoryImpl;
import com.solvd.underground.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;
    private final CarriageService carriageService;

    public TrainServiceImpl() {
        this.trainRepository = new TrainRepositoryImpl();
        this.carriageService = new CarriageServiceImpl();
    }

    @Override
    public Train create(Train train, Long depotId) {
        train.setId(null);

        trainRepository.create(train, depotId);

        if (train.getCarriages() != null) {
            List<Carriage> carriages = train.getCarriages().stream()
                    .map(carriage -> carriageService.create(carriage, train.getId()))
                    .collect(Collectors.toList());
            train.setCarriages(carriages);
        }
        return train;
    }

    @Override
    public Train findTrain() {
        return trainRepository.findTrain()
                .orElseThrow(() -> new QueryException("No trains found."));
    }

    @Override
    public void update(Train train, Long id, Long depotId) {
        trainRepository.update(train, id, depotId);
    }

    @Override
    public void delete(int id) {
        trainRepository.delete((long) id);
    }
}