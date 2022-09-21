package com.solvd.underground.service.impl;

import com.solvd.underground.domain.exception.QueryException;
import com.solvd.underground.domain.rollingstock.Carriage;
import com.solvd.underground.persistence.CarriageRepository;
import com.solvd.underground.persistence.impl.CarriageRepositoryImpl;
import com.solvd.underground.service.CarriageService;

public class CarriageServiceImpl implements CarriageService {

    private final CarriageRepository carriageRepository;

    public CarriageServiceImpl() {
        this.carriageRepository = new CarriageRepositoryImpl();
    }

    @Override
    public Carriage create(Carriage carriage, Long trainId) {
        carriage.setId(null);
        carriageRepository.create(carriage, trainId);
        return carriage;
    }

    @Override
    public void create(Carriage carriage) {
        carriageRepository.create(carriage);
    }

    @Override
    public void update(Carriage carriage, Long id) {
        carriageRepository.update(carriage, id);
    }

    @Override
    public Carriage findCarriage(Long trainId) {
        return carriageRepository.findCarriage(trainId).orElseThrow(() -> new QueryException("Carriages not found."));
    }

    @Override
    public void delete(int id) {
        carriageRepository.delete((long) id);
    }
}