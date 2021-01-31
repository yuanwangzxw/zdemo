package com.example.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.OrderMapper;

import java.util.List;

import com.example.entity.Order;
import com.example.service.OrderService;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public int updateBatch(List<Order> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<Order> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Order record) {
        return baseMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Order record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
