package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-05 21:27
 */
object RddAggregateByKey {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File - RDD"))
        val dataRdd: RDD[(String, Int)] = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("c", 3),
            ("b", 4), ("c", 5), ("c", 6)), 2)
        //aggregateByKey 方法有两个参数列表需要传递参数 第一个参数列表传递计算的初始值
        //      第二个参数列表中传递参数seqOp ：分区内的计算规则,相同key的value的计算
        //                             combOp ：分区间的计算规则,相同key的value的计算
        println(dataRdd.aggregateByKey(0)(_ + _, _ + _).collect().mkString(","))
        //foldByKey  如果分区内计算规则和分区间计算规则相同 可以使用foldByKey代替aggregateByKey
        println(dataRdd.foldByKey(0)(_ + _).collect().mkString(","))

        sc.stop()
    }
}
