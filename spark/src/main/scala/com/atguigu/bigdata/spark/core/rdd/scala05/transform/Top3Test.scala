package com.atguigu.bigdata.spark.core.rdd.scala05.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-06 10:57
 */
object Top3Test {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Rdd-Top3"))
        val dataRdd: RDD[String] = sc.textFile("input/agent.log")

//        val result = dataRdd.map(data => {
//            val line: Array[String] = data.split(" ")
//            (line(1) + " " + line(4), 1)
//        }).reduceByKey(_ + _).map(data => {
//            val strings: Array[String] = data._1.split(" ")
//            (strings(0), (strings(1), data._2))
//        }).groupByKey().mapValues(_.toList.sortWith((left, right) => left._2 > right._2).take(3))
//        result.collect().foreach(println)



    }
}
