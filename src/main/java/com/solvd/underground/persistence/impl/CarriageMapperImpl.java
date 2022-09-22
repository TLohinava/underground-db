package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.rollingstock.Carriage;
import com.solvd.underground.persistence.CarriageRepository;
import com.solvd.underground.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CarriageMapperImpl implements CarriageRepository {

    @Override
    public void create(Carriage carriage) {

    }

    @Override
    public List<Carriage> readAll() {
        return null;
    }

    @Override
    public Carriage read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CarriageRepository mapper = session.getMapper(CarriageRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public void update(Carriage carriage, Long id) {

    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CarriageRepository mapper = session.getMapper(CarriageRepository.class);
            mapper.delete(id);
        }
    }
}
