package com.solvd.underground.persistence.impl.jdbc;

import com.solvd.underground.domain.exception.ConnectionException;
import com.solvd.underground.domain.exception.UnsupportedOperationException;
import com.solvd.underground.domain.rollingstock.Carriage;
import com.solvd.underground.persistence.*;

import java.sql.*;
import java.util.*;

public class CarriageRepositoryImpl implements CarriageRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Carriage carriage) {
        throw new UnsupportedOperationException("This operation is not supported with the given amount of arguments");
    }

    @Override
    public void create(Carriage carriage, Long trainId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Insert into carriages(train_id, seat_capacity, manufacturer, carriage_number) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, trainId);
            statement.setInt(2, carriage.getSeatCapacity());
            statement.setString(3, carriage.getManufacturer());
            statement.setInt(4, carriage.getNumber());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                carriage.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in carriages: creation failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Carriage getById(Long id, List<Carriage> carriages) {
        return carriages.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Carriage carriage = new Carriage();
                    carriage.setId(id);
                    carriages.add(carriage);
                    return carriage;
                });
    }

    public static List<Carriage> mapRow(ResultSet rs, List<Carriage> carriages) throws SQLException {
        Long id = rs.getLong("carriage_id");
        if (id != 0) {
            if (carriages == null) {
                carriages = new ArrayList<>();
            }
            Carriage carriage = getById(id, carriages);
            carriage.setSeatCapacity(rs.getInt("seat_capacity"));
            carriage.setManufacturer(rs.getString("manufacturer"));
            carriage.setNumber(rs.getInt("carriage_number"));
        }
        return carriages;
    }

    public static List<Carriage> mapCarriages(ResultSet rs) throws SQLException {
        List<Carriage> carriages = new ArrayList<>();
        while (rs.next()) {
            carriages = mapRow(rs, carriages);
        }
        return carriages;
    }

    public Optional<Carriage> read(Long trainId) {
        Optional<Carriage> carriage;
        Connection connection = CONNECTION_POOL.getConnection();

        String query = "Select c.id as carriage_id, c.seat_capacity, c.carriage_number, c.manufacturer from carriages c where train_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, trainId);
            ResultSet set = statement.executeQuery();

            List<Carriage> carriages = mapCarriages(set);
            carriage = carriages.stream()
                    .findFirst();

        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in carriages: reading failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return carriage;
    }

    @Override
    public void update(Carriage carriage, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Update carriages set seat_capacity = ?, manufacturer = ?, carriage_number = ? where id = ?")) {
            statement.setInt(1, carriage.getSeatCapacity());
            statement.setString(2, carriage.getManufacturer());
            statement.setInt(3, carriage.getNumber());
            statement.setLong(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in carriages: update failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Delete from carriages where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in carriages: deletion failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}