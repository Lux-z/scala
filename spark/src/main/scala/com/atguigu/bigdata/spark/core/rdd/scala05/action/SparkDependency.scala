package com.atguigu.bigdata.spark.core.rdd.scala05.action

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-07 21:01
 */
object SparkDependency {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-Rdd")
        val sc = new SparkContext(conf)
        val rdd: RDD[String] = sc.makeRDD(List("hello scala","hello spark"))

    }
}
