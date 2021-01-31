package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.entity.AccountDO;
import com.example.service.AccountService;
import com.example.service.feign.AccountFeignService;
import com.zxw.ftp.spring.boot.autoconfigure.HelloService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-06-02
 */
@RestController
@RequestMapping("/account-do")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountFeignService accountFeignService;

    @Autowired
    private HelloService helloService;

    @GetMapping("/list")
    public List<AccountDO> list() {
        return accountService.list();
    }

    @GetMapping("/update/{info}")
    @GlobalTransactional
    public boolean update(@PathVariable("info") String info) {
        log.info("XID:{}", RootContext.getXID());
        AccountDO one = accountService.getOne(new LambdaQueryWrapper<AccountDO>().eq(AccountDO::getId, 1), false);
        boolean update1 = accountService.update(new LambdaUpdateWrapper<AccountDO>()
                .eq(AccountDO::getId, 1)
                .set(AccountDO::getMoney, one.getMoney().add(BigDecimal.ONE))
        );
        accountFeignService.update();
        if ("rollback".equals(info)) {
            throw new RuntimeException("故意回滚");
        }
        return update1;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloService.sayHello(name);
    }
}

