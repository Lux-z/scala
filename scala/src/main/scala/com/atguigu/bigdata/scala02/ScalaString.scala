package com.atguigu.bigdata.scala02

/**
 * @author lux_zhang
 * @create 2020-05-19 20:40
 */
object ScalaString {
    //scala中没有String类,其中使用的String为Java中的String类
    def main(args: Array[String]): Unit = {
        //插值字符串
        val name = "zhangsan"
        println(s"name is $name")

        //多行字符串： 三对双引号
        //可以直接封装JSON
       val s =  """
          |select *
          |from manager
          |where salary > $12000
        """.stripMargin
        println(s)
    }
}
