package com.atguigu.bigdata.scala03

/**
 * @author lux_zhang
 * @create 2020-05-21 14:16
 */
object ScalaHellFunction_ {
    def main(args: Array[String]): Unit = {
        //函数可以作为参数传递给其他的函数
        def fun(i:Int) :Int = {
            i / 2
        }

        def fun1(f:(Int)=>Int,i:Int = 0)= {
            f(i)
        }

        val f = fun _
        println(fun1(f, 10))

        //将函数作为参数使用时，可以使用匿名函数

        val f2 = fun1((i:Int)=>{i * 6},8)
        println(f2)

        //能省则省
        //逻辑代码只有一行，可以省略大括号
        val f3 = fun1((i:Int)=>i * 6,8)
        //参数类型能推断，参数类型可省略
        val f4 = fun1((i)=>i * 6,8)
        //匿名函数参数列表只有一个或没有，小括号可以省略
        val f5 = fun1( i =>i * 6,8 )
        //匿名函数中参数在逻辑代码中只是用一次，参数可省略,用_代替参数
        val f6 = fun1(_ * 6,8)

    }
}
