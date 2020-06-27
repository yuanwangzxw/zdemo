package example;

import java.util.ArrayList;
import java.util.List;

public class generic {

    public static void main(String[] args) {
        //创建对象时，定义
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        //使用：从集合取出元素时，不需要强转
        arr.forEach(x-> System.out.println(x+1));
    }
}

//类上泛型，通过创建对象指定类型,使用对象方法时避免强转
class A<T,E>{
    private T a;

    public A(T t) {
        this.a = t;
    }
}

//泛型继承：1.子类也是泛型类，则保持一致，子类不是泛型类，则指定类型
class B<T,E,K,V> extends A<T,E>{

    public B(T o) {
        super(o);
    }
}

class C extends A<String,Integer>{

    public C(String s) {
        super(s);
    }
}

//泛型接口,和类上泛型的继承是一样的
interface Fruit<T,E>{
    void print(T t, E e);
}

//实现类也是泛型，保持一致
class Apple<T, E, K, V> implements Fruit<T, E> {
    @Override
    public void print(T t, E e) {
        System.out.println(t+":"+e);
    }
}

//实现类不是泛型，则指定类型
class Strawberry implements Fruit<String, Integer> {
    @Override
    public void print(String s, Integer integer) {
        System.out.println(s+":"+integer);
    }
}

//方法泛型：类上泛型与接口泛型都是在创建对象时，指定类型，方法则是在调用方法时指定
class ExtMethod{

    public static void main(String[] args) {
        new ExtMethod().getItem(3);
    }

    public <T> T getItem(T t) {
        return t;
    }
}

//泛型边界符：用在对已声明泛型的类指定泛型边界，比如集合,comparable
//PECS pro(方法返回)用extends，consumer(方法入参)用super
class Ext{

    public static void main(String[] args) {
        //super指定入参类型为Integer或是父类，引用指向的实参,则需要是Integer或子类，避免向上强转，因为子类引用指向父类对象，可能调用到不存在的方法
        List<? super Integer> list = new ArrayList<>();
        list.add(3);
//        list.add(number);       //这里接收Integer的父类就会报错

        //指定返回类型是Number或子类，那只能用Number或父类去接收，这里Integer接收就会报错
        List<? extends Number> list2 = new ArrayList<>();
//        Integer n = list2.get(0);
        Number number = list2.get(0);
    }
}

