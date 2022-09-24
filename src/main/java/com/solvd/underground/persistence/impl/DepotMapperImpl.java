package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.persistence.DepotRepository;
import com.solvd.underground.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class DepotMapperImpl implements DepotRepository {
    @Override
    public void create(Depot depot) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            DepotRepository mapper = session.getMapper(DepotRepository.class);
            mapper.create(depot);
        }
    }

    @Override
    public Optional<Depot> read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            DepotRepository mapper = session.getMapper(DepotRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public void update(Depot depot, Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            DepotRepository mapper = session.getMapper(DepotRepository.class);
            mapper.update(depot, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            DepotRepository mapper = session.getMapper(DepotRepository.class);
            mapper.delete(id);
        }
    }
}