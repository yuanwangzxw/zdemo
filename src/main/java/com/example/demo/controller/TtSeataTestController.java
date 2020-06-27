package com.example.demo.controller;


import com.example.demo.entity.dos.TtSeataTestDO;
import com.example.demo.service.TtSeataTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxw
 * @since 2020-06-01
 */
@RestController
@RequestMapping("/seata")
public class TtSeataTestController {

    @Autowired
    private TtSeataTestService ttSeataTestService;

    @GetMapping("/list")
    public List<TtSeataTestDO> list(){
        return ttSeataTestService.list();
    }

    @PostMapping("/save")
    public void save(@RequestBody TtSeataTestDO ttSeataTestDO) {
        ttSeataTestService.save(ttSeataTestDO);
    }
}

