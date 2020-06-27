package com.example.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("pro9001")
public interface AccountFeignService {

    @GetMapping("/account-do/update")
    boolean update();
}
