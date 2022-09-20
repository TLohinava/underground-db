package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.rollingstock.Train;
import com.solvd.underground.persistence.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainRepositoryImpl implements TrainRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Train train) {
    }

    @Override
    public void create(Train train, Long depotId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Insert into trains(depot_id, number) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, depotId);
            statement.setInt(2, train.getNumber());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                train.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with the creation: " + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Train getById(Long id, List<Train> trains) {
        return trains.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Train train = new Train();
                    train.setId(id);
                    trains.add(train);
                    return train;
                });
    }

    public static List<Train> mapRow(ResultSet rs, List<Train> trains) throws SQLException {
        Long id = rs.getLong("train_id");
        if (id != 0) {
            if (trains == null) {
                trains = new ArrayList<>();
            }
            Train train = getById(id, trains);
            train.setNumber(rs.getInt("train_number"));
            train.setCarriages(CarriageRepositoryImpl.mapCarriages(rs));
        }
        return trains;
    }

    public static List<Train> mapTrains(ResultSet rs) throws SQLException {
        List<Train> trains = new ArrayList<>();
        return mapRow(rs, trains);
    }

    public Train findTrain() {
        Train train;
        Connection connection = CONNECTION_POOL.getConnection();

        String query = "Select t.id as train_id, t.number as train_number from  trains t";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            List<Train> trains = new ArrayList<>();
            train = new Train();
            while (set.next()) {
                Long trainId = set.getLong("train_id");
                train = getById(trainId, trains);
                train.setId(trainId);
                train.setNumber(set.getInt("train_number"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return train;
    }

    @Override
    public void update(Train train, Long depotId) {

    }

    @Override
    public void update(Train train, Long id, Long depotId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Update trains set depot_id = ?, number = ? where id = ?")) {
            statement.setLong(1, depotId);
            statement.setLong(2, train.getNumber());
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
        try (PreparedStatement statement = connection.prepareStatement("Delete from trains where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}