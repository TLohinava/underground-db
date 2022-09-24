package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Carriage;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface CarriageRepository {

    void create(@Param("carriage") Carriage carriage, @Param("trainId") Long trainId);

    Optional<Carriage> read(Long id);

    void update(@Param("carriage") Carriage carriage, @Param("id") Long id);

    void delete(Long id);

}
