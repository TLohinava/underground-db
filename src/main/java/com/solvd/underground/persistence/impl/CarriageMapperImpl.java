package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.rollingstock.Carriage;
import com.solvd.underground.persistence.CarriageRepository;
import com.solvd.underground.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

public class CarriageMapperImpl implements CarriageRepository {

    @Override
    public void create(Carriage carriage, Long trainId) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CarriageRepository mapper = session.getMapper(CarriageRepository.class);
            mapper.create(carriage, trainId);
        }
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
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CarriageRepository mapper = session.getMapper(CarriageRepository.class);
            mapper.update(carriage, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CarriageRepository mapper = session.getMapper(CarriageRepository.class);
            mapper.delete(id);
        }
    }
}
