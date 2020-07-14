package com.atguigu.bigdata.scala09

/**
 * @author lux_zhang
 * @create 2020-05-29 18:53
 */
object ScalaCollectionMethod {
    def main(args: Array[String]): Unit = {
        val list = List(1,2,3,4)
        val list1 = List(3,4,5,6)

        //并集、交集、差集
//        println(list.union(list1))
//        println(list.intersect(list1))
//        println(list.diff(list1))

        //关联集合 zip 通过集合下标关联 zipWithIndex 集合和下标自关联
        val listZip: List[(Int, Int)] = list.zip(list1)
        println(listZip)
        println(list.zipWithIndex)

        //将数据的一部分作为整体使用
        val iterator: Iterator[List[Int]] = list.union(list1).sliding(3,3)
        while (iterator.hasNext) println(iterator.next())

    }
}
