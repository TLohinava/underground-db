package com.solvd.underground.persistence;

import com.solvd.underground.domain.exception.ConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new ConnectionException("ConnectionException in Config." + e);
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}