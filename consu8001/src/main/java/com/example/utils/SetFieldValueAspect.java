package com.example.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Aspect
public class SetFieldValueAspect {

    private BeanUtil beanUtil;

    @Around("@annotation(NeedSetValue)")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        if (proceed instanceof Collection) {

        }
//        beanUtil.setFieldValue(proceed);
        return proceed;
    }
}
