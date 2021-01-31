package com.zxw.consu.controller;

import com.zxw.consu.config.CrmSetMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 */
@RestController
@RequestMapping("/test")
public class ConvertController {

    /**
     * 需求：根据id远程调用查名字，或根据当前对象的属性转换出对应的名字，如何封装？
     * 1.调用这个组件时，如何知道要设置哪些值
     * 2.需要调用哪个bean的哪个方法，传参是什么
     * 3.如果写工具类，让别人调用，相当于入侵业务了（违返了原则，单一，开闭需要设计模式，比如代理）
     * @return
     */
    @GetMapping("/convert")
    @CrmSetMethod
    public ConvertRespVo convert() {
        return new ConvertRespVo().setEnableStatus("009");
    }

    public String getVal(String key){
        if ("009".equals(key)) {
            return "启用";
        }
        return "禁用";
    }
}

