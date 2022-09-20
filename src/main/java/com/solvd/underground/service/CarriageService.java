package com.solvd.underground.service;

import com.solvd.underground.domain.rollingstock.Carriage;

public interface CarriageService {

    Carriage create(Carriage carriage, Long trainId);

    Carriage update(Carriage carriage, Long id, Long trainId);

    void delete(int id);

}