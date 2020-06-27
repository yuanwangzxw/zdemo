package com.zxw.mybatis.service.impl;

import com.zxw.mybatis.entity.Account;
import com.zxw.mybatis.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    void page() {
        List<Account> page = accountService.page(1, 5);
        System.out.println("page = " + page);
    }
}