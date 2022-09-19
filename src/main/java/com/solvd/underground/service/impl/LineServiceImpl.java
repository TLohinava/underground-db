package com.solvd.underground.service.impl;

import com.solvd.underground.domain.structure.*;
import com.solvd.underground.persistence.*;
import com.solvd.underground.persistence.impl.*;
import com.solvd.underground.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class LineServiceImpl implements LineService {

    private final LineRepository lineRepository;
    private final StationService stationService;
    private final DepotService depotService;

    public LineServiceImpl() {
        this.lineRepository = new LineRepositoryImpl();
        this.stationService = new StationServiceImpl();
        this.depotService = new DepotServiceImpl();
    }

    @Override
    public Line create(Line line, Long depotId) {
        line.setId(null);
        lineRepository.create(line, depotId);

        if (line.getStations() != null) {
            List<Station> stations = line.getStations().stream()
                    .map(stationService::create)
                    .collect(Collectors.toList());
            line.setStations(stations);
        }

        if (line.getDepot() != null) {
            Depot newDepot = depotService.create(line.getDepot());
            line.setDepot(newDepot);
        }
        return line;
    }

    @Override
    public List<Line> getAll() {
        return lineRepository.findAll();
    }

    @Override
    public void delete(int id) {
        lineRepository.delete((long) id);
    }
}