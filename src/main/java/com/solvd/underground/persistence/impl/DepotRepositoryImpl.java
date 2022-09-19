package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.persistence.*;

import java.sql.*;
import java.util.List;

public class DepotRepositoryImpl implements DepotRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Depot depot) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Insert into depots(address) values(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, depot.getAddress());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                depot.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with the creation: " + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Depot getById(Long id, List<Depot> depots) {
        return depots.stream()
                .filter(depot -> depot.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Depot depot = new Depot();
                    depot.setId(id);
                    depots.add(depot);
                    return depot;
                });
    }

    public static Depot mapRow(ResultSet rs) throws SQLException {
        Depot depot = new Depot();
        while (rs.next()) {
            depot.setId(rs.getLong("id"));
            depot.setAddress(rs.getString("address"));
            depot.setTrains(TrainRepositoryImpl.mapTrains(rs));
        }
        return depot;
    }

    @Override
    public void update(Depot depot, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Update depots set address = ? where id = ?")) {
            statement.setString(1, depot.getAddress());
            statement.setLong(2, id);
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
        try (PreparedStatement statement = connection.prepareStatement("Delete from depots where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}