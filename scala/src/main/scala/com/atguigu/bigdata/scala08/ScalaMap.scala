package com.atguigu.bigdata.scala08

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-05-27 21:03
 */
object ScalaMap {
    def main(args: Array[String]): Unit = {
        //构件键值对对象 ->
        //仍然默认不可变
        val map: Map[String, Int] = Map("a"->1,"b"->2,"c"->3)
        println(map.mkString(","))

        //可变map 导入mutable
        val map1: mutable.Map[String, Int] = mutable.Map("a"->1,"b"->2,"c"->3)
        // 添加数据
        map1.put("d", 4)
        // 修改数据
        //map.update("d", 5)
        map1("a") = 6
        // 删除数据
        map1.remove("a")
        //通过key指定获取value
        val maybeInt: Option[Int] = map1.get("a")
        //Option：选项类型：Some (有值)  None(无值)
        //println(maybeInt.get)//如果是None类型，取值会报异常

        //getOrElse获取数据，如果获取不到不会报错，返回默认值
        println(maybeInt.getOrElse("a", null))

    }

}
