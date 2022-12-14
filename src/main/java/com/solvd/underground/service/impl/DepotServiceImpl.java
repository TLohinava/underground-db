package com.solvd.underground.service.impl;

import com.solvd.underground.domain.exception.QueryException;
import com.solvd.underground.domain.rollingstock.Train;
import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.persistence.DepotRepository;
import com.solvd.underground.persistence.impl.mybatis.DepotMapperImpl;
import com.solvd.underground.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class DepotServiceImpl implements DepotService {

    private final DepotRepository depotRepository;
    private final TrainService trainService;

    public DepotServiceImpl() {
//        this.depotRepository = new DepotRepositoryImpl();
//        this.trainService = new TrainServiceImpl();
        this.trainService = new TrainServiceImpl();
        this.depotRepository = new DepotMapperImpl();
    }

    @Override
    public Depot create(Depot depot) {
        depot.setId(null);
        depotRepository.create(depot);
        if (depot.getTrains() != null) {
            List<Train> trains = depot.getTrains().stream()
                    .map(train -> trainService.create(train, depot.getId()))
                    .collect(Collectors.toList());
            depot.setTrains(trains);
        }
        return depot;
    }

    @Override
    public Depot findDepot(Long id) {
        return depotRepository.read(id)
                .orElseThrow(() -> new QueryException("No depots found."));
    }

    @Override
    public void update(Depot depot, Long id) {
        depotRepository.update(depot, id);
    }

    @Override
    public void delete(Long id) {
        depotRepository.delete(id);
    }
}