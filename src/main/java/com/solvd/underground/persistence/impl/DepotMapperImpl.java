package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.persistence.DepotRepository;
import com.solvd.underground.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepotMapperImpl implements DepotRepository {
    @Override
    public void create(Depot depot) {

    }

    @Override
    public Depot read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            DepotRepository mapper = session.getMapper(DepotRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public List<Depot> readAll() {
        return null;
    }

    @Override
    public void update(Depot depot, Long id) {

    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            DepotRepository mapper = session.getMapper(DepotRepository.class);
            mapper.delete(id);
        }
    }
}
