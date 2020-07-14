package com.atguigu.bigdata.scala04

/**
 * @author lux_zhang
 * @create 2020-05-22 19:01
 */
object ScalaClosureFunction {
    def main(args: Array[String]): Unit = {
        /*
         函数闭包原理
         函数在使用外部变量时，如果外部变量失效时，会将这个变量包含到当前的函数内部,形成闭合的使用效果。
         改变变量的生命周期称之为closure (闭包)
         1. scala2.12版本中，反编译后，发现编译器重新声明了内部函数的参数，将使用外部变量作为内部函数的参数使用
         2. 早期Scala版本（2.11），闭包会被编译为匿名函数类，如果使用外部变量，会将外部变量作为类的属性。
            早期Scala版本（2.11）,即使没有使用外部的变量，也会有闭包的效果，只是没有包含外部变量。
         3. Spark如何判断闭包：判断类名中是否为匿名函数类.
         4. 匿名函数肯定为闭包，将函数赋值给变量使用也是闭包，嵌套的内部函数在外部使用会有闭包
        */

        //嵌套函数
        def fun(i:Int) = {
            def test(j:Int) = i * j
            test _
        }

        //函数使用外部变量,形成闭包
        val value = 10
        def sum(i:Int) = value + i

    }
}
