package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-05 18:04
 */
object RddSample {
    def main(args: Array[String]): Unit = {
        //sample  根据指定的规则从数据集中抽取数据
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Rdd-Operator"))
        val dataRdd: RDD[Int] = sc.makeRDD(List(1,2,3,4),2)
        //抽取不放回，指定每个元素抽取概率
        println(dataRdd.sample(false, 0.5).collect().mkString(","))
        //抽取放回，指定每个元素被期望抽取到的次数
        println(dataRdd.sample(true, 2).collect().mkString(","))

        sc.stop()

    }
}
