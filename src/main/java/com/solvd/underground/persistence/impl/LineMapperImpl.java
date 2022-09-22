package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Line;
import com.solvd.underground.persistence.LineRepository;
import com.solvd.underground.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LineMapperImpl implements LineRepository {

    @Override
    public void create(Line line) {

    }

    @Override
    public Line read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            LineRepository mapper = session.getMapper(LineRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public List<Line> readAll() {
        return null;
    }

    @Override
    public void update(Line line, Long id) {

    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            LineRepository mapper = session.getMapper(LineRepository.class);
            mapper.delete(id);
        }
    }
}
