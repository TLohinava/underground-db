package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.structure.Depot;
import com.solvd.underground.persistence.*;

import java.sql.*;
import java.util.ArrayList;
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
                .filter(dep -> dep.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Depot depot = new Depot();
                    depot.setId(id);
                    depots.add(depot);
                    return depot;
                });
    }

    public static Depot mapDepot(ResultSet rs) throws SQLException {
        Long id = rs.getLong("depot_id");
        List<Depot> depots = new ArrayList<>();
        Depot depot = new Depot();
        if (id != 0) {
            depot = getById(id, depots);
            depot.setId(id);
            depot.setAddress(rs.getString("depot_address"));
            depot.setTrains(TrainRepositoryImpl.mapTrains(rs));
        }
        return depot;
    }

    public Depot findDepot() {
        Depot depot;
        Connection connection = CONNECTION_POOL.getConnection();

        String query = "Select d.id as depot_id, d.address as depot_address, \n" +
                "t.id as train_id, t.number as train_number, c.id as carriage_id, \n" +
                "c.seat_capacity, c.carriage_number, c.manufacturer \n" +
                "from depots d \n" +
                "left join trains t on d.id = t.depot_id \n" +
                "left join carriages c on t.id = c.train_id \n";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            List<Depot> depots = new ArrayList<>();
            depot = new Depot();
            while (set.next()) {
                Long depotId = set.getLong("depot_id");
                depot = getById(depotId, depots);
                depot.setId(depotId);
                depot.setAddress(set.getString("depot_address"));
                depot.setTrains(TrainRepositoryImpl.mapTrains(set));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
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