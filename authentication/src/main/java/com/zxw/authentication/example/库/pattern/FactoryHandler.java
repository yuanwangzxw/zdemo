package com.zxw.authentication.example.库.pattern;

/**
 * 责任链工厂模式
 *
 * @author zxw
 * @date 2020-10-03 23:19
 **/
public class FactoryHandler {

    public static BaseGatewayHandler getFirstHandler(){
        BaseGatewayHandler limitHandler = new LimitHandler();
        limitHandler.setNextGatewayHandler(new ConversionHandler());
        return limitHandler;
    }

    public static void main(String[] args) {
        getFirstHandler().service();
    }
}

abstract class BaseGatewayHandler {

    protected BaseGatewayHandler nextGatewayHandler;

    public void setNextGatewayHandler(BaseGatewayHandler nextGatewayHandler) {
        this.nextGatewayHandler = nextGatewayHandler;
    }

    protected abstract void service();

}
class ConversionHandler extends BaseGatewayHandler {
    @Override
    protected void service() {
        System.out.println("会话处理器");
    }
}
class LimitHandler extends BaseGatewayHandler{
    @Override
    protected void service() {
        System.out.println("限速处理器");
        nextGatewayHandler.service();
    }
}
