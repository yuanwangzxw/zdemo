package com.zxw.authentication.example.库.pattern;

import java.lang.reflect.Method;

/**
 * 动态代理
 *
 * @author zxw
 * @date 2020-10-06 19:28
 **/
public class MyProxy {

    public static void main(String[] args) {
//        UserService userService = new $Proxy0(new MyInvocationHandler(new UserServiceImpl()));
//        userService.find();
    }

}

interface MyInvokeHandler {
    Object invoke(Object obj, Method method, Object[] args);
}

interface UserService {
    void find();
}

class UserServiceImpl implements UserService {
    @Override
    public void find() {
        System.out.println("查询所有");
    }
}
//class $Proxy0 implements UserService{
//    private MyInvocationHandler myInvocationHandler;
//
//    public $Proxy0(MyInvocationHandler myInvocationHandler) {
//        this.myInvocationHandler = myInvocationHandler;
//    }
//    @SneakyThrows
//    @Override
//    public void find() {
//        Class<?> clz = this.getClass().getInterfaces()[0];
//        Method method = clz.getDeclaredMethod("find");
//        System.out.println("代理增强方法");
//        this.myInvocationHandler.invoke(this, method, new Object[]{});
//    }
//}
//class MyInvocationHandler{
//    private Object target;
//
//    public MyInvocationHandler(Object target) {
//        this.target = target;
//    }
//    /**代理对象，源方法，方法参数*/
//    @SneakyThrows
//    public Object invoke(Object proxy, Method method, Object[] args) {
//        return method.invoke(target, args);
//    }
//}
