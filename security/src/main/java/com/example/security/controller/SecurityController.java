package com.example.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PreDestroy;

/**
 * 安全控制器
 *
 * @author zxw
 * @date 2020-11-19 21:15
 **/
@RestController
@RequestMapping
public class SecurityController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('admins')")
    public String hello() {
        return "security hello";
    }

    @GetMapping("/permit")
    @PreAuthorize("isAnonymous()")
    public String permit() {
        return "permit";
    }

    @GetMapping("/delay")
    @PreAuthorize("hasAnyRole('vip','vip2')")
    public String delay() {
        return "delay";
    }

    @GetMapping("/delay2")
    @PreAuthorize("isAuthenticated()")
    public String delay2() {
        return "delay2";
    }
}
