package com.zxw.consu.config;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CrmSetMethodAop {

    @Autowired
    private BeanUtil beanUtil;

    @SneakyThrows
    @Around("@annotation(CrmSetMethod)")
    public Object around(ProceedingJoinPoint pjp) {
        Object proceed = pjp.proceed();
        beanUtil.setFieldValue(proceed);
        return proceed;
    }
}
