package com.atguigu.bigdata.spark.core.rdd.scala

import java.text.SimpleDateFormat

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-02 21:26
 */
object SparkRddDisk {
    def main(args: Array[String]): Unit = {
        val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("File-RDD")
        val sc = new SparkContext(sparkConf)

        //从磁盘中创建rdd
        val fileRDD: RDD[String] = sc.textFile("input")
        fileRDD.collect().foreach(println)

        sc.stop()
    }
}
