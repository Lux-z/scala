package com.atguigu.bigdata.scala08

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-05-27 19:09
 */
object ScalaSet {
    def main(args: Array[String]): Unit = {
        //scala数据集 set  默认集合是不可变集合
        //特征：无序，不可重复
        val list = List(1,1,2,2,3,3,4,4,5,6,6,7)
        //直接转化set实现去重
        val set: Set[Int] = list.toSet
        val newSet: Set[Int] = set + 8
        println(set.mkString(","))

        //可变set集合 导入mutable包
        val set1: mutable.Set[Int] = mutable.Set(1,2,3,4,5)
        //添加数据 add()  +(使用+添加数据会产生新的集合)   +=(+=不会产生新的集合)
        set1.add(6)

        //修改数据 true:类似于添加数据   false：类似于删除数据
        set1.update(7, true)
        println(set1)

        // 删除数据 -  -= 类比 +  +=
        set1.remove(6)
//        set1 - 3
//        set1 -= 3
    }

}
