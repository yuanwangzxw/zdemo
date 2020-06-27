package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Order;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    int updateBatch(List<Order> list);

    int batchInsert(@Param("list") List<Order> list);

    int insertOrUpdate(Order record);

    int insertOrUpdateSelective(Order record);
}