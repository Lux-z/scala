package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-02 21:16
 */
object SparkRddMemory {
    def main(args: Array[String]): Unit = {
        val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("RDD")
        val sc = new SparkContext(sparkConf)

        //从内存创建rdd
        val list = List(1,2,3,4)
        //val rdd: RDD[Int] = sc.parallelize(list)
        val rdd: RDD[Int] = sc.makeRDD(list)
        rdd.collect().foreach(println)

        sc.stop()
    }
}
