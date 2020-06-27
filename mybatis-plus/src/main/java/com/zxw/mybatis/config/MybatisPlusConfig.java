package com.zxw.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zxw.mybatis.mapper")
public class MybatisPlusConfig {
}
