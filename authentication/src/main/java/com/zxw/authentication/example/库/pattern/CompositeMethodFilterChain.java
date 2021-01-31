package com.zxw.authentication.example.库.pattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;

/**
 * @author zxw 经典责任链模式
 * @date 2020-10-02 12:03
 *
 *
 **/
@Data
@BaseInterceptorOrder(order=1)
abstract class BaseInterceptor {

    private Integer order;

    abstract boolean processBefore(ProceedingJoinPoint proceedingJoinPoint);
}

/**
 * 自定义注解，给拦截器排序
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface BaseInterceptorOrder {
    int order() default 0;
}

/**
 * 日志拦截器
 */
@Slf4j
@BaseInterceptorOrder(order = 1)
@Component
class LogInterceptor extends BaseInterceptor {

    @Override
    public boolean processBefore(ProceedingJoinPoint proceedingJoinPoint) {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        log.info("方法名:[{}]",method.getName());
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("方法参数:[{}]",args);
        return true;
    }
}
/**
 * 自定义方法拦截器链,可以在aop中执行chain的processBefore方法
 * 这里chain也继承了BaseInterceptor用了组合模式
 *
 * @author zxw
 * @date 2020-10-02 12:02
 **/
@Component
public class CompositeMethodFilterChain extends BaseInterceptor {

    @Autowired
    private List<BaseInterceptor> baseInterceptorList;

    @PostConstruct
    public void sortInterceptorList(){
        if (!CollectionUtils.isEmpty(baseInterceptorList)) {
            for (BaseInterceptor baseInterceptor : baseInterceptorList) {
                baseInterceptor.setOrder(resolveOrder(baseInterceptor));
            }
            baseInterceptorList.sort(Comparator.comparing(BaseInterceptor::getOrder));
        }
    }

    private Integer resolveOrder(BaseInterceptor baseInterceptor) {
        BaseInterceptorOrder baseInterceptorOrder = baseInterceptor.getClass().getAnnotation(BaseInterceptorOrder.class);
        if (baseInterceptorOrder != null) {
            return baseInterceptorOrder.order();
        }
        return 0;
    }

    @Override
    public boolean processBefore(ProceedingJoinPoint proceedingJoinPoint) {
        if (!CollectionUtils.isEmpty(baseInterceptorList)) {
            for (BaseInterceptor baseInterceptor : baseInterceptorList) {
                if (!baseInterceptor.processBefore(proceedingJoinPoint)) {
                    return false;
                }
            }
        }
        return true;
    }
}

