package com.atguigu.bigdata.scala08

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-05-27 21:35
 */
object ScalaTuple {
    def main(args: Array[String]): Unit = {
        //元组：Tuple 采用小括号（元素的组合）
        val tuple: (Int, String, Int, String) = (1,"zhangsan",2,"lisi")
        //元组中最多能放多22条数据,不限定元素的类型
        //获取元组数据
        tuple._1
        tuple._2
        //遍历：productIterator
        val iterator: Iterator[Any] = tuple.productIterator
        while (iterator.hasNext) println(iterator.next())
        //通过索引调用数据 由此可见元组中的数据有顺序
        println(tuple.productElement(0))
        // 如果元组中的元素只有2个，这样的元组称之为对偶元组  键值对底层就是偶元组
        val map: mutable.Map[String, Int] = mutable.Map( ("a", 1), ("b", 2), ("c", 3) )
        for (kv<-map) {
            println(kv._1 + "=" + kv._2)
        }
    }

}
