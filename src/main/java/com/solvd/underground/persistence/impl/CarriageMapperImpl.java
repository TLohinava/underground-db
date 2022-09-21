package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.rollingstock.Carriage;

import java.util.List;

public interface CarriageMapperImpl {

    Carriage create();

    Carriage read(Long id);

    List<Carriage> readAll();

    void update(Carriage carriage, Long id);

    void delete(Long id);
}
