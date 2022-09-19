package com.solvd.underground.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;
    private final BlockingQueue<Connection> connections;

    private ConnectionPool(int poolSize) {
        connections = new LinkedBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connections.add(createConnection());
        }
    }

    private Connection createConnection() {
        try {
            Class.forName(Config.getValue("driver"));
            return DriverManager.getConnection(Config.getValue("url"), Config.getValue("username"), Config.getValue("password"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Connection getConnection() {
        try {
            return connections.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool(Integer.parseInt(Config.getValue("poolSize")));
        }
        return INSTANCE;
    }
}