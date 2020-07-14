package com.atguigu.bigdata.scala03

/**
 * @author lux_zhang
 * @create 2020-05-21 14:16
 */
object ScalaHellFunction {
    def main(args: Array[String]): Unit = {
        //函数可以作为对象赋值给变量
        def fun1() = {
            "zhangsan"
        }
        // 将函数赋值给变量，那么这个变量其实就是函数，可以调用
        // 函数如果没有参数列表，那么调用时可以省略小括号
        // 如果此时希望将函数不执行，而是当成一个整体赋值给变量，那么需要使用下划线
        val f1 = fun1 _
        println(f1())

        // 如果不想使用下划线明确将函数作为整体使用，那么也可以直接声明变量的类型为函数
        // 函数类型： 参数列表 => 返回值类型
        val f2:()=>String = fun1
        println(f2())

        def fun2(i:Int) = {
            i * 10
        }
        val f3:(Int)=>Int = fun2
        println(f3(2))
    }

}
