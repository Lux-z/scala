package com.atguigu.bigdata.scala08

/**
 * @author lux_zhang
 * @create 2020-05-28 9:54
 */
object WordCountExer {
/*
数据 ：
     List(
         ("hello", 4),
         ("hello spark", 3),
         ("hello spark scala", 2),
         ("hello spark scala hive", 1)
     )

要求 ： 1. 将上面的数据进行WordCount后排序取前三名！
        2. 使用2种不同的方式。
 */
    def main(args: Array[String]): Unit = {
        val list = List(
            ("hello", 4),
            ("hello spark", 3),
            ("hello spark scala", 2),
            ("hello spark scala hive", 1)
        )
        //方式一：先将list中的元素映射成纯字符串数据，再根据单词聚合排序等
        //参数列表提示： ctrl + p
        val result: List[(String, Int)] = list.map(kv => ((kv._1 + " ") * kv._2)).
                flatMap(_.split(" ")).
                groupBy(word => word).
                map(kv => (kv._1, kv._2.length)).
                toList.sortBy(-_._2).take(3)
        println(result)


        //方式二：list中的数据存在规律，只要将集合中每一个kv的k提取处理，v相加即可
        val result1: List[(String, Int)] = list.flatMap(kv => (kv._1.split(" ").map((_, kv._2)))).
                groupBy(_._1).
                map(kv => (kv._1, kv._2.map(_._2).sum)).
                toList.sortBy(-_._2).take(3)
        println(result1)





    }
}
