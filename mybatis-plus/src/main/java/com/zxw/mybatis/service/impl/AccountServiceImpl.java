package com.zxw.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxw.mybatis.entity.Account;
import com.zxw.mybatis.mapper.AccountMapper;
import com.zxw.mybatis.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService{

    @Override
    public int updateBatch(List<Account> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int batchInsert(List<Account> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(Account record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(Account record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public List<Account> page(Integer pageNo, Integer pageSize) {
        return baseMapper.page((pageNo - 1) * pageSize, pageSize);
    }
}
