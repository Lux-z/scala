package com.atguigu.bigdata.scala06;

/**
 * @author lux_zhang
 * @create 2020-05-25 20:27
 */
public class JavaMethodOverload {
    public static void main(String[] args) {
        //test调用重载方法，参数个数相同，看参数类型；如果找不到对应的类型，会从类树往上查找
        //Person man = new Man();
        //test(person);
        Man man = new Man();
        test(man);
    }
    public static void test(Person person){
        System.out.println("person...");
    }

//    public static void test(Man man){
//        System.out.println("man...");
//    }
}
class Person {

}
class Man extends Person {

}