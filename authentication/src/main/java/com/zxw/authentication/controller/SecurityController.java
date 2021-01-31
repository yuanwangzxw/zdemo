package com.zxw.authentication.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 安全控制器
 *
 * @author zxw
 * @date 2020-11-19 20:37
 **/
@RestController
@RequestMapping("/security")
@Api(tags = "安全控制器")
public class SecurityController {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }
}
