package com.zxw.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    public DataSource dataSource() {
        return new DruidDataSource();
    }
}
