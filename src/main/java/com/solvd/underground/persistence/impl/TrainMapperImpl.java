package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.rollingstock.Train;
import com.solvd.underground.persistence.MyBatisConfig;
import com.solvd.underground.persistence.TrainRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TrainMapperImpl implements TrainRepository {

    @Override
    public void create(Train train) {

    }

    @Override
    public Train read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            TrainRepository mapper = session.getMapper(TrainRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public List<Train> readAll() {
        return null;
    }

    @Override
    public void update(Train train, Long id) {

    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            TrainRepository mapper = session.getMapper(TrainRepository.class);
            mapper.delete(id);
        }
    }
}
