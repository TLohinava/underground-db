package com.solvd.underground;

import com.solvd.underground.domain.rollingstock.Carriage;
import com.solvd.underground.persistence.impl.CarriageMapperImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory;
        CarriageMapperImpl carriageMapperImpl;
        Reader reader = null;
        try {
            reader = Resources
                    .getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            carriageMapperImpl = sqlSessionFactory.openSession().getMapper(CarriageMapperImpl.class);
//            List<Carriage> carriages = carriageMapperImpl.readAll();
            Carriage carriage = carriageMapperImpl.read((long) 60);
            System.out.println(carriage.getId());
            System.out.println(carriage.getNumber());
            System.out.println(carriage.getManufacturer());
        } catch (IOException e) {
            throw new RuntimeException("aaaa");
        }

    }
}
