package com.solvd.underground.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisConfig {

    private static final String CONFIG_PATH = "mybatis-config.xml";
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(CONFIG_PATH);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}