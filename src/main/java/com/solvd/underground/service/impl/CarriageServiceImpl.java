package com.solvd.underground.service.impl;

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
    public Carriage create(Carriage carriage, Long train_id) {
        carriage.setId(null);
        carriageRepository.create(carriage, train_id);
        return carriage;
    }

    @Override
    public void delete(int id) {
        carriageRepository.delete((long) id);
    }
}