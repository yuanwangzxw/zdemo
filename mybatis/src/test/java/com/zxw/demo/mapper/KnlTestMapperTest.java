package com.zxw.demo.mapper;

import com.zxw.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,classes = DemoApplication.class)
class KnlTestMapperTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void findByGuid() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }
}