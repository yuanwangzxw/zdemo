package com.zxw.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.zxw.demo.mapper.AccountMapper;
import com.zxw.demo.entity.Account;
import com.zxw.demo.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{

    @Resource
    private AccountMapper accountMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return accountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Account record) {
        return accountMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(Account record) {
        return accountMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Account record) {
        return accountMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(Account record) {
        return accountMapper.insertSelective(record);
    }

    @Override
    public Account selectByPrimaryKey(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Account record) {
        return accountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Account record) {
        return accountMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<Account> list) {
        return accountMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<Account> list) {
        return accountMapper.batchInsert(list);
    }

}
