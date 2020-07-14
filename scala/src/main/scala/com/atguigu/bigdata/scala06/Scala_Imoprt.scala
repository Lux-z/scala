package com.atguigu.bigdata.scala06

/**
 * @author lux_zhang
 * @create 2020-05-25 18:17
 */
object Scala_Imoprt {
    def main(args: Array[String]): Unit = {
        //import可以用来导入对象,但是只能导入val声明的对象
        val test = new test
        import test._
        user
        println(name)

    }
}

class test {
    val name : String = "zhangsan"
    def user: Unit ={
        println("user......")
    }
}
