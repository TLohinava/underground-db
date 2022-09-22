package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Carriage;

import java.util.List;

public interface CarriageRepository {

    void create(Carriage carriage);

    Carriage read(Long id);

    List<Carriage> readAll();

    void update(Carriage carriage, Long id);

    void delete(Long id);

}
