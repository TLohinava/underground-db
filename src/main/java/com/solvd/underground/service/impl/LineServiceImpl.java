package com.solvd.underground.service.impl;

import com.solvd.underground.domain.exception.QueryException;
import com.solvd.underground.domain.structure.*;
import com.solvd.underground.persistence.*;
import com.solvd.underground.persistence.impl.mybatis.LineMapperImpl;
import com.solvd.underground.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class LineServiceImpl implements LineService {

    private final LineRepository lineRepository;
    private final StationService stationService;
    private final DepotService depotService;

    public LineServiceImpl() {
//        this.lineRepository = new LineRepositoryImpl();
//        this.stationService = new StationServiceImpl();
//        this.depotService = new DepotServiceImpl();
        this.lineRepository = new LineMapperImpl();
        this.stationService = new StationServiceImpl();
        this.depotService = new DepotServiceImpl();
    }

    @Override
    public Line create(Line line) {
        line.setId(null);

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
        lineRepository.create(line);
        line.getStations().forEach(st -> lineRepository.createStationConnection(line, st));
        return line;
    }

    @Override
    public Line read(Long id) {
        return lineRepository.read(id)
                .orElseThrow(() -> new QueryException("No line found."));
    }

    @Override
    public List<Line> getAll() {
        return lineRepository.readAll();
    }

    @Override
    public void update(Line line, Long id) {
        lineRepository.update(line, id);
    }

    @Override
    public void delete(Long id) {
        lineRepository.delete(id);
    }
}