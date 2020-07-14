package com.atguigu.bigdata.spark.core.rdd.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-04 16:19
 */
object SparkRddOperatorFilter {
    def main(args: Array[String]): Unit = {
        //从服务器日志数据apache.log中获取2015年5月17日的请求路径
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File-RddFilter"))
        sc.textFile("input/apache.log").
                map(_.split(" ")(3)).
                filter(_.substring(0,10) == "17/05/2015").
                collect().foreach(println)
    }
}
