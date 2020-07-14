package com.atguigu.bigdata.scala03

import scala.util.control.Breaks

/**
 * @author lux_zhang
 * @create 2020-05-20 19:15
 */
object ScalaLoop {
    def main(args: Array[String]): Unit = {
        //循环守卫：增加条件来决定是否继续循环体的执行
        for (i <- Range(1, 5) if i != 3) {
            println(i)
        }

        println("===============================================")

        //嵌套循环
        //当外层循环没有执行代码时，嵌套循环可以简化至以下情况
        for (i <- 1 to 5; j = i) {
            println(s"sum = ($i + $j) = ${i + 2 * j}")
        }

        println("===============================================")

        //循环结构作为对象，也是有返回值的，当需要调用循环结构的返回值，需要使用关键字yield
        val a = for (i <- Range(1, 5) if i != 3) yield {
            println(i)
        }
        println(a)
        println("===============================================")
        //循环中断scala中没有break关键字，通过调用方法Breaks.break()来实现中断循环(import scala.util.control.Breaks)
        Breaks.breakable(
            for (i <- 1 to 5) {
                if (i == 4) {
                    Breaks.break()
                }
                println(i * 2)
            }
        )
    }
}
