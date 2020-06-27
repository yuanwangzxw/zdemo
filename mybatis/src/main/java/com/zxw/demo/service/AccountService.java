package com.zxw.demo.service;

import java.util.List;
import com.zxw.demo.entity.Account;
public interface AccountService{


    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int updateBatch(List<Account> list);

    int batchInsert(List<Account> list);

}
