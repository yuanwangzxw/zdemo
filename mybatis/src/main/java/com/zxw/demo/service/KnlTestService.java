package com.zxw.demo.service;

import com.zxw.demo.entity.KnlTest;
import java.util.List;
public interface KnlTestService{


    int deleteByPrimaryKey(Long guid);

    int insert(KnlTest record);

    int insertOrUpdate(KnlTest record);

    int insertOrUpdateSelective(KnlTest record);

    int insertSelective(KnlTest record);

    KnlTest selectByPrimaryKey(Long guid);

    int updateByPrimaryKeySelective(KnlTest record);

    int updateByPrimaryKey(KnlTest record);

    int updateBatch(List<KnlTest> list);

    int batchInsert(List<KnlTest> list);

}
