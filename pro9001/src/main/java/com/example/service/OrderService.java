package com.example.service;

import java.util.List;

import com.example.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {


    int updateBatch(List<Order> list);

    int batchInsert(List<Order> list);

    int insertOrUpdate(Order record);

    int insertOrUpdateSelective(Order record);

}
