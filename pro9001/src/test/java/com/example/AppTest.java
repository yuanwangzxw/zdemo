package com.example;

import static org.junit.Assert.assertTrue;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Autowired
    private DataSource dataSource;

    @SneakyThrows
    @Test
    public void shouldAnswerWithTrue() {
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getCatalogs();
        while (resultSet.next()) {
            System.out.println("database = " + resultSet.getString(1));
        }
    }
}
