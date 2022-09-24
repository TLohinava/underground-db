package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Line;
import com.solvd.underground.persistence.LineRepository;
import com.solvd.underground.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class LineMapperImpl implements LineRepository {

    @Override
    public void create(Line line) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            LineRepository mapper = session.getMapper(LineRepository.class);
            mapper.create(line);
        }
    }

    @Override
    public Optional<Line> read(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            LineRepository mapper = session.getMapper(LineRepository.class);
            return mapper.read(id);
        }
    }

    @Override
    public List<Line> readAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            LineRepository mapper = session.getMapper(LineRepository.class);
            return mapper.readAll();
        }
    }

    @Override
    public void update(Line line, Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            LineRepository mapper = session.getMapper(LineRepository.class);
            mapper.update(line, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            LineRepository mapper = session.getMapper(LineRepository.class);
            mapper.delete(id);
        }
    }
}