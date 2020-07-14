package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-05 20:55
 */
object ScalaDoubleValue {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File - RDD"))
        val rdd1: RDD[Int] = sc.makeRDD(List(1,3,5,7),2)
        val rdd2: RDD[Int] = sc.makeRDD(List(1,3,2,4,6,8),3)
        val rdd3: RDD[String] = sc.makeRDD(List("a","b","c","d"),2)
        //union 并集 会将分区合并  必须保证rdd的数据类型一致
        rdd1.union(rdd2).saveAsTextFile("output1")
        //intersection 交集 保留最大分区数，会进行suffer 必须保证rdd的数据类型一致
        rdd1.intersection(rdd2).saveAsTextFile("output2")
        //subtract 差集 分区数量等于当前rdd的分区数量 会进行suffer 必须保证rdd的数据类型一致
        rdd1.subtract(rdd2).saveAsTextFile("output3")
        //zip 拉链  必须保证分区数一致 可以和数据类型不一致的rdd进行 拉链操作
        rdd1.zip(rdd3).saveAsTextFile("output4")

        sc.stop()
    }
}
