package com.atguigu.bigdata.java;

import com.atguigu.bigdata.scala.Scala_variable$;

/**
 * @author lux_zhang
 * @create 2020-05-18 21:37
 */
public class StaticBlockTest {
    public static void main(String[] args) {
        System.out.println(test.i);
        String sex = Scala_variable$.MODULE$.sex();
        System.out.println(sex);
    }
}
