package com.zxw.authentication.example.库.pattern;

/**
 * 简单工厂测试
 *
 * @author zxw
 * @date 2020-12-19 20:11
 **/
public class SimpleFactoryTest {
    public static void main(String[] args) {
        PayWay wxPay = PayWaySimpleFactory.createPayWay("wxPay");
    }
}
interface PayWay{

    void underfiedOrder();
}
class WechatPay implements PayWay{
    @Override
    public void underfiedOrder() {
        System.out.println("wx支付");
    }
}
class PayWaySimpleFactory{

    public static PayWay createPayWay(String payWay) {
        if ("wxPay".equals(payWay)) {
            return new WechatPay();
        }
        return null;
    }
}