package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.domain.structure.Line;
import com.solvd.underground.domain.structure.Station;
import com.solvd.underground.persistence.ConnectionPool;
import com.solvd.underground.persistence.LineRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineRepositoryImpl implements LineRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Line line) {
    }

    @Override
    public void create(Line line, Long depotId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Insert into `lines`(depot_id, name) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, line.getDepot().getId());
            statement.setString(2, line.getName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                line.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with the creation: " + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Line getById(Long id, List<Line> lineList) {
        return lineList.stream()
                .filter(line -> line.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Line newLine = new Line();
                    newLine.setId(id);
                    lineList.add(newLine);
                    return newLine;
                });
    }

    public static List<Line> mapLines(ResultSet rs) throws SQLException {
        List<Line> lines = new ArrayList<>();
        while (rs.next()) {
            Long id = rs.getLong(1);
            Line line = getById(id, lines);
            line.setName(rs.getString(3));

            List<Station> stations = StationRepositoryImpl.mapStations(rs);
            line.setStations(stations);

            line.setDepot(DepotRepositoryImpl.mapRow(rs));
        }
        return lines;
    }

    public List<Line> findAll() {
        List<Line> lines;
        Connection connection = CONNECTION_POOL.getConnection();

        String query = "Select l.id, l.depot_id as depot_id, l.name, t.number as train_number, c.carriage_number as carriage_number \n" +
                "from `lines` l \n" +
                "left join depots d on l.depot_id = d.id \n" +
                "left join trains t on d.id = t.depot_id \n" +
                "left join carriages c on t.id = c.train_id \n";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            lines = mapLines(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lines;
    }

    @Override
    public void update(Line line, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Update lines set depot_id = ?, name = ? where id = ?")) {
            statement.setLong(1, line.getDepot().getId());
            statement.setString(2, line.getName());
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Delete from lines where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}