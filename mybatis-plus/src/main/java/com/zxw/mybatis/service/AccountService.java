package com.zxw.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxw.mybatis.entity.Account;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
public interface AccountService extends IService<Account>{


    int updateBatch(List<Account> list);

    int batchInsert(List<Account> list);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    List<Account> page( @RequestParam("pageNo") Integer pageNo, Integer pageSize);
}
