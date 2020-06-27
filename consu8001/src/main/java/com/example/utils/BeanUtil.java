package com.example.utils;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

public class BeanUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @SneakyThrows
    public void setFieldValue(Collection collection) {
        Class<?> clazz = collection.iterator().next().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field needField : fields) {
            NeedSetValue anno = needField.getAnnotation(NeedSetValue.class);
            if (anno == null) {
                continue;
            }
            needField.setAccessible(true);
            Object bean = this.applicationContext.getBean(anno.beanClass());
            Method method = anno.beanClass().getMethod(anno.methodName(), clazz.getDeclaredField(anno.param()).getType());
            Field paramField = clazz.getDeclaredField(anno.param());
            paramField.setAccessible(true);
            boolean needInnerField = !StringUtils.isEmpty(anno.targetField());
            Field targetField = null;

            for (Object o : collection) {
                Object paramValue = paramField.get(o);
                if (paramValue == null) {
                    continue;
                }
                Object value = method.invoke(bean, paramValue);
                if (needInnerField) {
                    if (value != null) {
                        if (targetField == null) {
                            targetField = value.getClass().getDeclaredField(anno.targetField());
                            targetField.setAccessible(true);
                        }
                        value = targetField.get(value);
                        needField.set(o, value);
                    }

                }
            }
        }
    }
}
