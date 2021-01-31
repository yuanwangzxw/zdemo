package com.zxw.authentication.example.库.pattern;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者测试
 *
 * @author zxw
 * @date 2020-10-06 17:59
 **/
public class ObserverTest {
    public static void main(String[] args) {
        WeChatSubject weChatSubject = new WeChatSubject();
        WeChatObServer weChatObServer = new WeChatObServer();
        weChatSubject.addObServer(weChatObServer);
        weChatSubject.notifyObServer("天");
    }
}
abstract class AbstractObServer<T>{
    abstract void callback(T data);
}
class WeChatObServer extends AbstractObServer<String>{

    @Override
    void callback(String data) {
        System.out.println("data = " + data);
    }
}
@Data
abstract class AbstractSubject<T>{

    private List<AbstractObServer<T>> list=new ArrayList<>();

    void addObServer(AbstractObServer<T> abstractObServer) {
        list.add(abstractObServer);
    }

    void removeObServer(AbstractObServer<T> abstractObServer) {
        list.remove(abstractObServer);
    }

    void  notifyObServer(T t) {
        for (AbstractObServer<T> abstractObServer : list) {
            abstractObServer.callback(t);
        }
    }
}
class WeChatSubject extends AbstractSubject<String>{}
