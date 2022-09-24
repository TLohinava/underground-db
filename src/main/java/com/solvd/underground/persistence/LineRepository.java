package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Line;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface LineRepository {

    void create(Line line);

    Optional<Line> read(Long id);

    List<Line> readAll();

    void update(@Param("line") Line line, @Param("id") Long id);

    void delete(Long id);

}
