package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Carriage;

public interface CarriageRepository {

    void create(Carriage carriage);

    Carriage read(Long id);

    void update(Carriage carriage, Long id);

    void delete(Long id);

}
