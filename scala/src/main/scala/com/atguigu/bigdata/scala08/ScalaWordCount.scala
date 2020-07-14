package com.atguigu.bigdata.scala08

import scala.io.Source

/**
 * @author lux_zhang
 * @create 2020-05-27 18:29
 */
object ScalaWordCount {
    def main(args: Array[String]): Unit = {
        val tuples: List[(String, Int)] = Source.fromFile("input/word.txt").getLines().
                toList.flatMap(_.split(" ")).
                groupBy(word => word).map(kv => (kv._1, kv._2.length)).toList.sortBy(-_._2).take(3)
        tuples.foreach(println(_))


    }
}
