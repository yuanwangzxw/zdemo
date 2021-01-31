package com.zxw.authentication.example.库.pattern;

/**
 * 装饰者:
 *
 * @author zxw
 * @date 2020-10-04 22:18
 **/
public class DecoratorExer {
    public static void main(String[] args) {
        //奶泡与豆浆是装饰对象，咖啡是被装饰对象，都实现同一接口，装饰类持有被装饰类的接口
        Beverage beverage = new Whip(new Soy(new Caffeine()));
        System.out.println("beverage.cost() = " + beverage.cost());
    }
}
interface Beverage{
    String getDesc();
    Integer cost();
}
class Caffeine implements Beverage{
    @Override
    public String getDesc() {return "咖啡";}
    @Override
    public Integer cost() {return 2;}
}
class Soy implements Beverage{
    private Beverage beverage;
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDesc() {return "豆浆";}
    @Override
    public Integer cost() {return 3+beverage.cost();}
}
class Whip implements Beverage{
    private Beverage beverage;
    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDesc() {return "奶泡";}
    @Override
    public Integer cost() {return 4+beverage.cost();}
}
