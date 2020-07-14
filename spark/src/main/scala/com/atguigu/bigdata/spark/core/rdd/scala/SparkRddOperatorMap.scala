package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-03 20:20
 */
object SparkRddOperatorMap {
    def main(args: Array[String]): Unit = {
        //转换算子  通过方法（算子）转换RDD，但是不会执行
        val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("File-Rdd"))
//        val rdd: RDD[Int] = sc.makeRDD(List(1,2,3,4))
//        val rdd1: RDD[Int] = rdd.map(_ * 2)
//        rdd1.saveAsTextFile("output")
        //collect() 行动算子  执行作业的方法
//        rdd1.collect()

        //从服务器日志数据apache.log中获取用户 请求URL资源路径
        sc.textFile("input/apache.log").map(_.split(" ")(6)).collect().foreach(println)

        sc.stop()


    }
}
