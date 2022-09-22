package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Station;
import com.solvd.underground.persistence.MyBatisConfig;
import com.solvd.underground.persistence.StationRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StationMapperImpl implements StationRepository {

    @Override
    public void create(Station station) {

    }

    @Override
    public Station read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            StationRepository mapper = session.getMapper(StationRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public List<Station> readAll() {
        return null;
    }

    @Override
    public void update(Station station, Long id) {

    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            StationRepository mapper = session.getMapper(StationRepository.class);
            mapper.delete(id);
        }
    }
}
