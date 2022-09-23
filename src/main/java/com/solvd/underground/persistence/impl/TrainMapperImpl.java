package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.rollingstock.Train;
import com.solvd.underground.persistence.MyBatisConfig;
import com.solvd.underground.persistence.TrainRepository;
import org.apache.ibatis.session.SqlSession;

public class TrainMapperImpl implements TrainRepository {

    @Override
    public void create(Train train, Long depotId) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            TrainRepository mapper = session.getMapper(TrainRepository.class);
            mapper.create(train, depotId);
        }
    }

    @Override
    public Train read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            TrainRepository mapper = session.getMapper(TrainRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public void update(Train train, Long id, Long depotId) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            TrainRepository mapper = session.getMapper(TrainRepository.class);
            mapper.update(train, id, depotId);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            TrainRepository mapper = session.getMapper(TrainRepository.class);
            mapper.delete(id);
        }
    }
}