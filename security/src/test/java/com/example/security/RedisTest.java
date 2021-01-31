package com.example.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zxw
 * @date 2021-01-04 12:47
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void test() {
        Object num = redisTemplate.opsForValue().get("num");
        System.out.println(num);
    }
}
