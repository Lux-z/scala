package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-03 20:20
 */
object SparkRddOperatorMapPartitions {
    def main(args: Array[String]): Unit = {

        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("RddMapPartitions"))
        val dataRdd: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6,7,8),3)

        //mapPartitions 以分区为单位获取分区的所有数据
        //获取每个数据分区的最大值
//        val rdd: RDD[Int] = dataRdd.mapPartitions(iter => {
//            List(iter.max).toIterator
//        })
//        println(rdd.collect().mkString(","))


        //mapPartitionsWithIndex 可以获取mapPartitions数据的分区号

        //获取指定数据分区的数据
        val rdd: RDD[Int] = dataRdd.mapPartitionsWithIndex((index, iter) => {
            if (index == 1) iter
            else Nil.iterator
        })

        println(rdd.collect().mkString(","))
        sc.stop()
    }
}
