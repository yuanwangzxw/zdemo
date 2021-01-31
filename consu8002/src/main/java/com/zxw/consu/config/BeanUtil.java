package com.zxw.consu.config;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class BeanUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @SneakyThrows
    public void setFieldValue(Object o) {

        Class<?> clazz = o.getClass();
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            CrmSetVal crmSetVal = field.getAnnotation(CrmSetVal.class);
            if (crmSetVal == null) {
                continue;
            }
            field.setAccessible(true);
            Object bean = applicationContext.getBean(crmSetVal.beanClass());
            Field paramField = clazz.getDeclaredField(crmSetVal.param());
            Method method = crmSetVal.beanClass().getDeclaredMethod(crmSetVal.method(), paramField.getType());
            paramField.setAccessible(true);
            Object paramValue = paramField.get(o);
            Object target = method.invoke(bean, paramValue);

            field.set(o,target);
        }
    }
}
