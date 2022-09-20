package com.solvd.underground.service;

import com.solvd.underground.domain.rollingstock.Carriage;

public interface CarriageService {

    Carriage create(Carriage carriage, Long trainId);

    void update(Carriage carriage, Long id);

    void delete(int id);

    Carriage findCarriage(Long trainId);
}