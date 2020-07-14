package com.atguigu.bigdata.spark.core.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-01 18:37
 */
object SparkWordCountTest {
    def main(args: Array[String]): Unit = {
        //1.环境准备
        val wordCount: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
        //2.建立Spark连接
        val sc: SparkContext = new SparkContext(wordCount)
        //3.业务
        println(sc.textFile("input").
                flatMap(_.split(" ")).
                groupBy(word => word).
                map(kv => (kv._1, kv._2.size)).
                collect().
                mkString(","))
        println(sc.textFile("input").
                flatMap(_.split(" ")).
                map(word => (word, 1)).
                reduceByKey(_ + _).
                collect().
                mkString(","))

        //4.关闭环境
        sc.stop()
    }
}
