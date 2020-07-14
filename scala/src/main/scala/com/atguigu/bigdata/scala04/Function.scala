package com.atguigu.bigdata.scala04

/**
 * @author lux_zhang
 * @create 2020-05-22 18:21
 */
object Function {
    def main(args: Array[String]): Unit = {
        //函数作为返回值使用
        //使用下划线
        def test(i: Int) = i * 2

        def fun() = test _

        //调用,变量获取函数的返回值也是函数
        val a = fun()
        println(a(10))
        //至简原则
        println(fun()(10))

        //嵌套函数
        def fun1() = {
            def test1(i:Int) = i * 2
            test1 _
        }
        println(fun1()(10))


    }
}
