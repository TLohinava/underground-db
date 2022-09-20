package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Line;
import com.solvd.underground.persistence.ConnectionPool;
import com.solvd.underground.persistence.LineRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineRepositoryImpl implements LineRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Line line) {
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
            Long id = rs.getLong("line_id");
            Line line = getById(id, lines);
            line.setName(rs.getString("line_name"));

            line.setDepot(DepotRepositoryImpl.mapDepot(rs));
        }
        return lines;
    }

    public List<Line> findAll() {
        List<Line> lines;
        Connection connection = CONNECTION_POOL.getConnection();

        String query = "Select l.id as line_id, l.name as line_name, \n" +
                "d.id as depot_id, d.address as depot_address, t.id as train_id, t.number as train_number, \n" +
                "c.id as carriage_id, c.carriage_number, c.seat_capacity, c.manufacturer \n" +
                "from `lines` l \n" +
                "inner join depots d on l.depot_id = d.id \n" +
                "inner join trains t on d.id = t.depot_id \n" +
                "inner join carriages c on t.id = c.train_id \n";

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
        try (PreparedStatement statement = connection.prepareStatement("Update `lines` set depot_id = ?, name = ? where id = ?")) {
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
        try (PreparedStatement statement = connection.prepareStatement("Delete from `lines` where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}