package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Carriage;

public interface CarriageRepository extends BaseRepository<Carriage> {

    void create(Carriage carriage, Long trainId);

    void update(Carriage carriage, Long id, Long trainId);

}