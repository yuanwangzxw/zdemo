package com.zxw.authentication.example.库.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元模式
 *
 * @author zxw
 * @date 2020-12-18 12:32
 **/
public class FlywightPattern {

    private static Map<String,Product> map;

    static{
        map = new ConcurrentHashMap<>();
        map.put("1",new Product("手机",BigDecimal.TEN));
    }

    public static void main(String[] args) {
        Product product = map.get("1");
        product.setPrice(BigDecimal.ONE);
        System.out.println(map);
    }
}
@Data
@AllArgsConstructor
class Product{
    private String productName;
    private BigDecimal price;
}
