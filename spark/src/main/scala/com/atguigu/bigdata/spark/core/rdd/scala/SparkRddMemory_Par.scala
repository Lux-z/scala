package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-02 21:34
 */
object SparkRddMemory_Par {
    def main(args: Array[String]): Unit = {
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
        val sc = new SparkContext(sparkConf)

        //makeRDD的第一个参数：数据源
        //makeRDD的第二个参数：numSlices: Int = defaultParallelism（默认并行度）
        //RDD中分区数就是并行度，设定并行度，其实就是在设置分区数

        //scheduler.conf.getInt("spark.default.parallelism", totalCores)
        //并行度默认会从spark配置信息中获取spark.default.parallelism的值，
        //          如果获取不到，采用默认值totalCores（当前机器的总核数）
        //          当前机器的总核数 = 当前环境可用核数
        //local => 单核(单线程)
        //local[4] =>4核（4线程）
        //local[*] =>最大核数
        val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),4)
        //rdd.collect().foreach(println)

        //将RDD处理完的数据保存到分区文件中
        rdd.saveAsTextFile("output1")

        sc.stop()

        //Ctrl + h 查看 类的关系
    }
}
