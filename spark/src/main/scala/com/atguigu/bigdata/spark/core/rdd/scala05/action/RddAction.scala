package com.atguigu.bigdata.spark.core.rdd.scala05.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-07 18:51
 */
object RddAction {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-Rdd")
        val sc = new SparkContext(conf)
        val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))

        //reduce 计算结果
        println(rdd.reduce(_ + _))

        //collect  采集数据
        // collect方法会将所有分区计算的结果拉取到当前节点Driver的内存中，可能会出现内存溢出

        //count 返回rdd数据集的数量
        println(rdd.count())

        //first 返回rdd第一个数据 scala集合：head

        //take 返回rdd指定个数的数据

        //takeOrdered 排序后取指定个数的数据

        //sum 求和 返回值为Double

        //aggregate 初始值，分区内计算，分区间计算
        //初始值不仅参与分区内计算，也参与分区间的计算
        val rdd2: RDD[Int] = sc.makeRDD(List(1,2,3,4,5,6),2)
        rdd2.aggregate(0)(_ + _,_ + _)

        //fold aggregate的简化版，分区内计算逻辑和分区间计算逻辑相同时代替aggregate

        //countByKey  wordcount
        val rdd3: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("a",1),("a",1)))
        val result: collection.Map[String, Long] = rdd3.countByKey()
        //println(result)

        //countByValue 统计值出现的次数 wordcount
        println(sc.makeRDD(List("a", "a", "a", "b", "b")).countByValue())

        //foreach算子  foreach方法是在当前节点(Executor)的内存中完成数据的循环,可以将循环在不同的计算节点完成
        // 算子之外的代码是在Driver端执行

        sc.stop()
    }
}
