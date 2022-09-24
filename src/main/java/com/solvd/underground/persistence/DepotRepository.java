package com.solvd.underground.persistence;

import com.solvd.underground.domain.structure.Depot;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface DepotRepository {

    void create(Depot depot);

    Optional<Depot> read(Long id);

    void update(@Param("depot") Depot depot, @Param("id") Long id);

    void delete(Long id);

}
