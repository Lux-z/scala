package com.atguigu.bigdata.scala04

/**
 * @author lux_zhang
 * @create 2020-05-22 19:44
 */
object ScalaRecursionFunction {
    def main(args: Array[String]): Unit = {
        //递归函数必须显示的声明函数的返回值
        //普通递归
        //此递归方式如果递归次数太多，会导致栈空间不足而抛出异常
        def test(i:Int):Int = {
            if (i < 1)
                1
            else
                i + test(i - 1)
        }
        println(test(100))

        //尾递归:递归调用自身后返回结果，释放内存，避免普通递归的弊端
        def test1(i: Int, result: Int = 0) : Int = {
            if (i < 1)
                result
            else
                test1(i - 1, i + result)
        }
        println(test1(100))

        //惰性函数：延迟加载，用到数据的时候加载数据
        //声明方式：调用函数前加关键字lazy
        def input()= {
            println("=======first========")
            "waibiwaibi"
        }

        lazy val data = input
        println("hold on!!!")
        println(data)
    }

}
