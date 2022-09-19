package com.solvd.underground.service;

import com.solvd.underground.domain.employee.Driver;

public interface DriverService {

    Driver create(Driver driver);

    void delete(int id);

}