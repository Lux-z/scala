package com.atguigu.bigdata.spark.test.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-07-06 19:20
 */
object WordCountTest {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("rdd_test")
        val context = new SparkContext(conf)
//        val result: RDD[(String, Int)] = context.textFile("input/city_info.txt").flatMap(_.split("\\t")).map((_,1)).reduceByKey(_+_)


        //println(result.toDebugString)
//        result.foreachPartition(println)
//        println("********************************")
//        result.foreach(println)

        val rdd= context.makeRDD(List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)),2)
        rdd.combineByKey(
            (_,1),
            (t:(Int,Int),v)=>(t._1+v,t._2+1),
            (t1:(Int,Int),t2:(Int,Int))=>(t1._1+t2._1,t1._2+t2._2)
        )
    }
}

