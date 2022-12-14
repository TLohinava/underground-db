package com.solvd.underground.persistence.impl.jdbc;

import com.solvd.underground.domain.exception.ConnectionException;
import com.solvd.underground.domain.exception.UnsupportedOperationException;
import com.solvd.underground.domain.rollingstock.Carriage;
import com.solvd.underground.domain.structure.Station;
import com.solvd.underground.persistence.*;

import java.sql.*;
import java.util.*;

public class StationRepositoryImpl implements StationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Station station) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Insert into stations(name) values(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, station.getName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                station.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in stations: creation failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Station getById(Long id, List<Station> stations) {
        return stations.stream()
                .filter(st -> st.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Station station = new Station();
                    station.setId(id);
                    stations.add(station);
                    return station;
                });
    }

    public static List<Station> mapRow(ResultSet rs, List<Station> stationList) throws SQLException {
        Long id = rs.getLong("id");
        if (id != 0) {
            if (stationList == null) {
                stationList = new ArrayList<>();
            }
            Station station = getById(id, stationList);
            station.setName(rs.getString("name"));
        }
        return stationList;
    }

    public static List<Station> mapStations(ResultSet rs) throws SQLException {
        List<Station> stations = new ArrayList<>();

        stations = mapRow(rs, stations);

        return stations;
    }

    @Override
    public Optional<Station> read(Long id) {
        throw new UnsupportedOperationException("This operation is not supported with the given amount of arguments");
    }

    @Override
    public void update(Station station, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Update stations set name = ? where id = ?")) {
            statement.setString(1, station.getName());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in stations: update failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("Delete from stations where id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ConnectionException("ConnectionException in stations: deletion failed." + e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}