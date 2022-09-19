package com.solvd.underground.service.impl;

import com.solvd.underground.domain.employee.Driver;
import com.solvd.underground.persistence.DriverRepository;
import com.solvd.underground.persistence.impl.DriverRepositoryImpl;
import com.solvd.underground.service.DriverService;

public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl() {
        this.driverRepository = new DriverRepositoryImpl();
    }

    @Override
    public Driver create(Driver driver) {
        driver.setId(null);
        driverRepository.create(driver);
        return driver;
    }

    @Override
    public void delete(int id) {
        driverRepository.delete((long) id);
    }
}