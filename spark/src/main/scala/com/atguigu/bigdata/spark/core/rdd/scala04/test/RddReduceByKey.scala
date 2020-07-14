package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-05 21:13
 */
object RddReduceByKey {
    def main(args: Array[String]): Unit = {
        //reduceByKey 根据数据的key进行分组，然后对value进行聚合 可以指定分区数
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File - RDD"))
        val dataRdd: RDD[(String, Int)] = sc.makeRDD(List(("hello", 1), ("scala",1), ("hello",1)))
        dataRdd.reduceByKey(_+_,2).saveAsTextFile("output1")
        //groupByKey 根据数据的key进行分组 只能将v聚合成集合，不能自定义规则
        dataRdd.groupByKey().map{case (word,list)=>(word,list.size)}

        sc.stop()
    }
}
