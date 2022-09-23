package com.solvd.underground.persistence;

import com.solvd.underground.domain.rollingstock.Carriage;
import org.apache.ibatis.annotations.Param;

public interface CarriageRepository {

    void create(@Param("carriage") Carriage carriage, @Param("trainId") Long trainId);

    Carriage read(Long id);

    void update(@Param("carriage") Carriage carriage, @Param("id") Long id);

    void delete(Long id);

}
