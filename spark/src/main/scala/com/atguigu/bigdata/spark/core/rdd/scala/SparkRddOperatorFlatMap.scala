package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-04 15:01
 */
object SparkRddOperatorFlatMap {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("File-Rdd"))
        //flatMap 扁平映射
        val dataRdd: RDD[Any] = sc.makeRDD(List(List(1,2,3),4,List(5,6)))
        println(dataRdd.flatMap(_ match {
            case list: List[_] => list
            case other => List(other)
        }).collect().mkString(","))
    }

}
