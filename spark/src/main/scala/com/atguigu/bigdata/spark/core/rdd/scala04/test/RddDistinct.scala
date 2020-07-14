package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-05 18:49
 */
object RddDistinct {
    def main(args: Array[String]): Unit = {
        //distinct 去重
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Rdd-Operator"))
        val dataRdd: RDD[Int] = sc.makeRDD(List(1,1,2,3,3,3,3,4,5),3)
        //distinct 参数 重新指定分区数，避免去重后的数据倾斜
        println(dataRdd.distinct(2).collect().mkString(","))

        sc.stop()
    }
}
