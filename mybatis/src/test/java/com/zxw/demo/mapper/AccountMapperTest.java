package com.zxw.demo.mapper;

import com.zxw.demo.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    void findByKid() {
        List<Account> list = accountMapper.findByKid(3L);
        System.out.println("list = " + list);
    }
}