package com.solvd.underground.persistence;

interface BaseRepository<T> {

    void create(T t);

    void update(T t, Long id);

    void delete(Long id);

}