package com.atguigu.bigdata.scala04

/**
 * @author lux_zhang
 * @create 2020-05-22 20:48
 */
object Function02 {
    def main(args: Array[String]): Unit = {
        //函数柯里化：多个参数列表
        //作用：参数解耦，将逻辑简单化可以支持更多的语法
        //声明方式：
        def test(i:Int)(j:Int)(f:(Int,Int)=>Int) = {
            f(i,j)
        }
        //调用
        test(1)(1)(_+_)

        //控制抽象
        //参数列表如果有多行逻辑，通过大括号代替小括号
        //scala支持将代码逻辑作为参数传递到函数中
        //声明方式：参数名:=>返回值类型
        def test1(op : => Unit)= {
            op
        }
        //调用
        test1(println(1 + 2))

    }
}
