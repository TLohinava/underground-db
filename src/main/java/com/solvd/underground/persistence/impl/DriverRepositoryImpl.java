package com.solvd.underground.persistence.impl;

import com.solvd.underground.persistence.ConnectionPool;
import com.solvd.underground.persistence.DriverRepository;
import com.solvd.underground.domain.employee.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepositoryImpl implements DriverRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Driver driver) {
        Connection connection = CONNECTION_POOL.getConnection();

        Date date = Date.valueOf(driver.getDob());
        try (PreparedStatement statement = connection.prepareStatement("Insert into drivers(first_name, last_name, dob) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, driver.getFirstName());
            statement.setString(2, driver.getLastName());
            statement.setDate(3, date);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                driver.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with the creation: " + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Driver getById(Long id, List<Driver> drivers) {
        return drivers.stream()
                .filter(st -> st.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Driver driver = new Driver();
                    driver.setId(id);
                    drivers.add(driver);
                    return driver;
                });
    }

    public static List<Driver> mapRow(ResultSet rs, List<Driver> driverList) throws SQLException {
        Long id = rs.getLong("id");
        if (id != 0) {
            if (driverList == null) {
                driverList = new ArrayList<>();
            }
            Driver driver = getById(id, driverList);
            driver.setFirstName(rs.getString("first_name"));
            driver.setFirstName(rs.getString("last_name"));
            driver.setDob(rs.getDate("dob").toLocalDate());
        }
        return driverList;
    }

    public static List<Driver> mapDrivers(ResultSet rs) throws SQLException {
        List<Driver> drivers = new ArrayList<>();

        while (rs.next()) {
            drivers = mapRow(rs, drivers);
        }
        return drivers;
    }

    @Override
    public void update(Driver driver, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Date date = Date.valueOf(driver.getDob());
        try (PreparedStatement statement = connection.prepareStatement("Update drivers set first_name = ?, last_name = ?, dob = ? where id = ?")) {
            statement.setString(1, driver.getFirstName());
            statement.setString(2, driver.getLastName());
            statement.setDate(3, date);
            statement.setLong(4, id);
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
        try (PreparedStatement statement = connection.prepareStatement("Delete from drivers where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}