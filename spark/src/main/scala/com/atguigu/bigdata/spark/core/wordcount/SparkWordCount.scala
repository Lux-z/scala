package com.atguigu.bigdata.spark.core.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-01 16:07
 */
object SparkWordCount {
    def main(args: Array[String]): Unit = {
        //1.Spark环境准备
        //setMaster：设定Spark环境的设置
        val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
        //2.建立和Spark的连接
        //jdbc:connection
        val sc = new SparkContext(sparkConf)
        //3.实现业务操作
        //textFile参数指定单个文件或者目录下的多个文件
        val dataRdd: RDD[String] = sc.textFile("input")
        //数据分组聚合
//        val result: RDD[(String, Int)] = dataRdd.
//                flatMap(_.split(" ")).groupBy(word=>word).map(kv=>(kv._1,kv._2.size))
        //改变数据结构使用reduceByKey()直接分组聚合
        val result: RDD[(String, Int)] = dataRdd.flatMap(_.split(" ")).map(word=>(word,1)).reduceByKey(_+_)

        //打印数据到控制台
        println(result.collect().mkString(","))
        //4.释放连接
        sc.stop()
    }

}
