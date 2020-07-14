package com.atguigu.bigdata.scala08

import scala.collection.mutable.ListBuffer

/**
 * @author lux_zhang
 * @create 2020-05-27 18:47
 */
object ScalaList {
    def main(args: Array[String]): Unit = {
        //scala集合 seq
        //默认情况下scala提供的集合都是不可变的  immutable 对集合的操作会产生新集合
        //声明方式
        val list: List[Int] = List(1,2,3)
        // 空集合 Nil
        println(Nil)
        //空集合一般用于增加数据
        //添加方式： 1::2::3::Nil 可以直接添加集合(添加的集合为一个整体)
        val list1: List[Any] = 4::5::6::list::Nil
        //扁平化：将集合中的数据拆分成个体
        val list2: List[Int] = 4::5::6::list:::Nil

        //可变集合：ListBuffer
        val buffer: ListBuffer[Int] = ListBuffer(1,2,3)
        // 增加数据
        buffer.append(5,6,7)
        buffer.insert(1,8)

        // 修改
        //buffer.update(1,9)
        //buffer(1) = 9

        // 删除数据
        //buffer.remove(1)
        buffer.remove(1,3)

        println(buffer)
        buffer.foreach(println)
        buffer.mkString(",")

    }

}
