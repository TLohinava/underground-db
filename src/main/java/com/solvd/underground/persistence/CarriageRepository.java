package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Carriage;

import java.util.Optional;

public interface CarriageRepository extends BaseRepository<Carriage> {

    void create(Carriage carriage, Long trainId);

    void update(Carriage carriage, Long id);

    Optional<Carriage> findCarriage(Long trainId);

}