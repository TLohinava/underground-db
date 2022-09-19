package com.solvd.underground.service;

import com.solvd.underground.domain.rollingstock.Carriage;

public interface CarriageService {

    Carriage create(Carriage carriage, Long train_id);

    void delete(int id);

}