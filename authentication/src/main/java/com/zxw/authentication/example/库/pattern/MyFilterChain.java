package com.zxw.authentication.example.库.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 责任链
 *
 * @author zxw
 * @date 2020-10-08 10:29
 **/
public class MyFilterChain {
    public static void main(String[] args) {
        MyInterceptorChain myInterceptorChain = new MyInterceptorChain();
        myInterceptorChain.addInterceptor(new InterceptorA());
        myInterceptorChain.addInterceptor(new InterceptorB());
        myInterceptorChain.plugin(new Object());
    }
}
interface MyInterceptor{
    Object plugin(Object target, MyInterceptorChain myInterceptorChain);
}
class InterceptorA implements MyInterceptor{
    @Override
    public Object plugin(Object target, MyInterceptorChain myInterceptorChain) {
        System.out.println("interceptorA");
//        return myInterceptorChain.plugin(target);
        return target;
    }
}
class InterceptorB implements MyInterceptor{
    @Override
    public Object plugin(Object target, MyInterceptorChain myInterceptorChain) {
        System.out.println("interceptorB");
        return myInterceptorChain.plugin(target);
//        return target;
    }
}
class MyInterceptorChain{
    private List<MyInterceptor> interceptorList = new ArrayList<>();
    //这个iterator要保证每个线程独有，不然遍历游标就会相互影响，目前还有问题
    private Iterator<MyInterceptor> iterator;

    public void addInterceptor(MyInterceptor myInterceptor){
        interceptorList.add(myInterceptor);
    }

    public Object plugin(Object target) {
        if (iterator == null) {
            iterator = interceptorList.iterator();
        }
        if (iterator.hasNext()) {
            MyInterceptor next = iterator.next();
            next.plugin(target, this);
        }
        return target;
    }
}
