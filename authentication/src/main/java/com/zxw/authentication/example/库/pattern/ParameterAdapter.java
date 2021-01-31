package com.zxw.authentication.example.库.pattern;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数适配器:原来参数是map，新要求传一个list，则给list写个adapter继承map就行了，并修改map某些方法
 *
 * @author zxw
 * @date 2020-10-04 09:21
 **/
public class ParameterAdapter {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        new OrderService().forMap(new ListAdapter(list));
    }
}
class OrderService{
    public void forMap(Map map) {
        for (int i = 0; i < map.size(); i++) {
            String val = (String) map.get(i);
            System.out.println("val = " + val);
        }
    }
}
@AllArgsConstructor
class ListAdapter extends HashMap {
    private List<String> list;

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Object get(Object key) {
        return list.get((Integer)key);
    }
}
