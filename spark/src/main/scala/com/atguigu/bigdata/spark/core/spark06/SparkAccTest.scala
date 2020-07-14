package com.atguigu.bigdata.spark.core.spark06

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 使用累加器完成数据的累加
 * @author lux_zhang
 * @create 2020-06-08 18:22
 */
object SparkAccTest {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-Acc")
        val sc = new SparkContext(conf)
        val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
        //声明累加器变量
        val sum: LongAccumulator = sc.longAccumulator("sum")
        //使用累加器
        rdd.foreach(sum.add(_))
        //返回数据
        println(sum.value)

        sc.stop()
    }
}
