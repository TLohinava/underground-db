package com.solvd.underground.persistence;

import java.util.Optional;

interface BaseRepository<T> {

    void create(T t);

    Optional<T> read(Long id);

    void update(T t, Long id);

    void delete(Long id);

}