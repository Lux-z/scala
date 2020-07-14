package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-04 15:27
 */
object SparkRddOperatorgroupBy {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File-Rdd"))
        //将List("Hello", "hive", "hbase", "Hadoop")根据单词首写字母进行分组
        val dataRdd: RDD[String] = sc.makeRDD(List("Hello", "hive", "hbase", "Hadoop"))
        dataRdd.groupBy(_.charAt(0)).collect().foreach(key=>println(s"${key._1},(${key._2.mkString(",")})"))

        //从服务器日志数据apache.log中获取每个时间段访问量
        val result: RDD[(String, Int)] = sc.textFile("input/apache.log").
                map(_.split(" ")(3)).
                groupBy(_.substring(11, 13)).
                mapValues(_.size)
        result.collect().foreach(println)

    }
}
