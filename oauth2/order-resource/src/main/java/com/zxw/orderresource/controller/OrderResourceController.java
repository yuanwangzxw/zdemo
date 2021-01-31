package com.zxw.orderresource.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * order控制器
 *
 * @author zxw
 * @date 2020-12-13 00:57
 **/
@RestController
@RequestMapping
public class OrderResourceController {

    @PostMapping("/test")
    public String success() {
        return "success";
    }

    @PostMapping("/permit")
    public String permit() {
        return "permit";
    }
}
