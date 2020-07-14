package com.atguigu.bigdata.spark.core.rdd.scala04.exer

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-05 19:31
 */
object Distinct {
    def main(args: Array[String]): Unit = {
        //不使用distinct实现去重
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Rdd-Operator"))
        val dataRdd: RDD[Int] = sc.makeRDD(List(1,1,2,3,3,3,3,4,5),3)
        println(dataRdd.groupBy(word => word).map(_._1).collect().mkString(","))

        sc.stop()
    }
}
