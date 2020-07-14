package com.atguigu.bigdata.scala09

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-05-29 19:13
 */
object ScalaCollectionMethod1 {
    def main(args: Array[String]): Unit = {
        //集合中的数据计算
        val list = List(1,2,3,4,5,6,7)
        //reduce 参数和返回值类型一致
        println(list.reduce(_ - _))
        //reduceLeft ((((((1-2)-3)-4)-5)-6)-7)
        println(list.reduceLeft(_ - _))
        //reduceRight (1-(2-(3-(4-(5-(6-7))))))
        println(list.reduceRight(_ - _))

        //fold()() 添加初始值 返回值类型取决于初始值的类型
        println(list.fold(8)(_ - _))
        //(((((((8-1)-2)-3)-4)-5)-6)-7)
        println(list.foldLeft(8)(_ - _))
        println(list.foldLeft("")(_ + _))
        //(1-(2-(3-(4-(5-(6-(7-8)))))))
        println(list.foldRight(8)(_ - _))
        println(list.foldRight("")(_ + _))

        //合并集合
        val map1 = mutable.Map("a"->1,"b"->2,"c"->3)
        val map2 = mutable.Map("c"->4,"d"->5,"a"->6)
        Map("a"->1,"b"->2)

        println(map1.foldLeft(map2)(
            (map, kv) => {
                map(kv._1) = map.getOrElse(kv._1,0) + kv._2
                // = map.updated(kv._1,map.getOrElse(kv._1,0) + kv._2)
                map
            }))

        //scan 类似于fold 但是会将处理过程的结果返回为一个集合
        println(list.scan(0)(_ - _))
        println(list.scanLeft(0)(_ - _))
        println(list.scanRight(0)(_ - _))

    }
}
