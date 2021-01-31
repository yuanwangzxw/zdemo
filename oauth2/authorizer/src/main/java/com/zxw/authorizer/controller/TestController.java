package com.zxw.authorizer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2020-12-20 14:18
 **/
@RestController
@RequestMapping
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
