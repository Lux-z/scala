package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-04 15:09
 */
object SparkRddOperatorGlom {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File-RddGlom"))
        //glom 将每个分区的数据转换为数组
        val dataRdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8),3)
//        val rdd: RDD[Array[Int]] = dataRdd.glom()
//        rdd.foreach(array=>println(array.mkString(",")))

        //计算所有分区最大值求和
        val rddArr: RDD[Array[Int]] = dataRdd.mapPartitions(iter => {
            List(iter.max).iterator
        }).glom()
        val sum: Int = rddArr.collect().flatten.sum
        println(sum)
        sc.stop()
    }
}
