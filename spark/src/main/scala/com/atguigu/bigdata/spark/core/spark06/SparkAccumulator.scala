package com.atguigu.bigdata.spark.core.spark06

import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-06-08 15:05
 */
object SparkAccumulator {
    def main(args: Array[String]): Unit = {
        //自定义累加器
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-Acc")
        val sc = new SparkContext(conf)
        val rdd: RDD[String] = sc.makeRDD(List("hello spark","hello scala","hello spark scala"))

        val acc = new MyAccumulator()
        sc.register(acc,"wordcount")
        rdd.flatMap(_.split(" ")).foreach(word=> acc.add(word))
        println(acc.value)

        sc.stop()

    }

    class MyAccumulator extends AccumulatorV2[String,mutable.Map[String,Int]] {
        var map: mutable.Map[String, Int] = mutable.Map[String,Int]()

        override def isZero: Boolean = map.isEmpty

        override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = {new MyAccumulator}

        override def reset(): Unit = map.clear()

        override def add(word: String): Unit = {
            map.update(word,map.getOrElse(word,0) + 1)
        }

        override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
            val map1 = map
            val map2 = other.value
            map = map1.foldLeft(map2)(
                (map,kv)=>{
                    map(kv._1) = map.getOrElse(kv._1,0) + kv._2
                    map
                }
            )
        }

        override def value: mutable.Map[String, Int] = map
    }
}
