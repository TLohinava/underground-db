package com.solvd.underground.persistence.impl;

import com.solvd.underground.domain.exception.ConnectionException;
import com.solvd.underground.domain.exception.UnsupportedOperationException;
import com.solvd.underground.domain.rollingstock.Train;
import com.solvd.underground.persistence.*;

import java.sql.*;
import java.util.*;

public class TrainRepositoryImpl implements TrainRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Train train) {
        throw new UnsupportedOperationException("This operation is not supported with the given amount of arguments");
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
            throw new ConnectionException("ConnectionException in trains: creation failed." + e);
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

    public Optional<Train> findTrain() {
        Optional<Train> train = Optional.empty();
        Connection connection = CONNECTION_POOL.getConnection();

        String query = "Select t.id as train_id, t.number as train_number, \n" +
                "c.id as carriage_id, c.carriage_number, c.manufacturer, c.seat_capacity from  trains t \n" +
                "left join carriages c on c.train_id = t.id";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                List<Train> trains = mapTrains(set);
                train = trains.stream()
                        .findFirst();
            }

        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in trains: reading failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return train;
    }

    @Override
    public void update(Train train, Long depotId) {
        throw new UnsupportedOperationException("This operation is not supported with the given amount of arguments");
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
            throw new ConnectionException("ConnectionException in trains: update failed." + e);
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
            throw new ConnectionException("ConnectionException in trains: deletion failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}