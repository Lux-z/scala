package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-05 19:05
 */
object RddCoalesce {
    def main(args: Array[String]): Unit = {
        //coalesce  根据数据量缩减分区，用于大数据集过滤后，提高小数据集的执行效率
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Rdd-Operator"))
        val dataRdd: RDD[Int] = sc.makeRDD(List(1,3,5,7,2,4,6,8,10),3).filter(_%2==0)
        dataRdd.saveAsTextFile("output1")
        dataRdd.coalesce(2).saveAsTextFile("output2")
        //扩大分区 repartition 相较于coalesce会进行suffer过程，使得数据更均衡
        dataRdd.repartition(4).saveAsTextFile("output3")

        sc.stop()
    }
}
