package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-20 17:30
 */
object test {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local").setAppName("rdd")
        val sc = new SparkContext(conf)
        val rdd: RDD[(Int, String)] = sc.makeRDD(List((1, "a"), (1, "a"), (1, "a"), (2, "b"), (3, "c"), (3, "c")))

        // 统计每种key的个数
        val result: collection.Map[Int, Long] = rdd.countByKey()
        result.foreach(println)
        sc.stop()
    }
}
