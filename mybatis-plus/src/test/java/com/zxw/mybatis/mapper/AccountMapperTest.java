package com.zxw.mybatis.mapper;

import com.zxw.mybatis.entity.Account;
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
    public void test() {
        Account obtain = accountMapper.obtain(new AccountMapper.AccountQuery(1));
        System.out.println("obtain = " + obtain);
    }


    @Test
    void idList() {
        List<String> idList = accountMapper.idList();
        System.out.println("idList = " + idList);
    }
}