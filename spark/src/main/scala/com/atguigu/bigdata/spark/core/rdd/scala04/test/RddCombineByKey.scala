package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-07 15:16
 */
object RddCombineByKey {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-Rdd")
        val sc = new SparkContext(conf)
        val dataRdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)),2)

        //combineByKey方法可以传递3个参数
        //第一个参数表示的就是将计算的第一个值转换结构
        //第二个参数表示分区内的计算规则
        //第三个参数表示分区间的计算规则
        dataRdd.combineByKey(
            data=>(data,1),
            (t:(Int,Int),v)=>(t._1+v,t._2 + 1),
            (t1: (Int, Int), t2: (Int, Int)) => {(t1._1 + t2._1, t1._2 + t2._2)}
        ).collect().foreach(print)
    }
}
