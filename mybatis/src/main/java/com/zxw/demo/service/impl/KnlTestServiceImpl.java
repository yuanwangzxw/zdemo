package com.zxw.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxw.demo.entity.KnlTest;
import java.util.List;
import com.zxw.demo.mapper.KnlTestMapper;
import com.zxw.demo.service.KnlTestService;
@Service
public class KnlTestServiceImpl implements KnlTestService{

    @Resource
    private KnlTestMapper knlTestMapper;

    @Override
    public int deleteByPrimaryKey(Long guid) {
        return knlTestMapper.deleteByPrimaryKey(guid);
    }

    @Override
    public int insert(KnlTest record) {
        return knlTestMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(KnlTest record) {
        return knlTestMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(KnlTest record) {
        return knlTestMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(KnlTest record) {
        return knlTestMapper.insertSelective(record);
    }

    @Override
    public KnlTest selectByPrimaryKey(Long guid) {
        return knlTestMapper.selectByPrimaryKey(guid);
    }

    @Override
    public int updateByPrimaryKeySelective(KnlTest record) {
        return knlTestMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(KnlTest record) {
        return knlTestMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<KnlTest> list) {
        return knlTestMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<KnlTest> list) {
        return knlTestMapper.batchInsert(list);
    }

}
