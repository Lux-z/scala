package com.atguigu.bigdata.spark.core.spark06

import com.atguigu.summer.framework.core.TApplication
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-08 21:20
 */
object SparkBroadCast extends TApplication{
    def main(args: Array[String]): Unit = {
        start("spark") {
            val sc: SparkContext = envData.asInstanceOf[SparkContext]
            val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
            val list = List(("a", 4), ("b", 5), ("c", 6))
            //声明广播变量
            val bc: Broadcast[List[(String, Int)]] = sc.broadcast(list)

            rdd1.map {
                case (word, count) => {
                    var count1 = 0
                    //使用广播变量
                    for (kv<-bc.value) if (kv._1 == word) count1 = kv._2
                    (word,(count,count1))
                }
            }.collect().foreach(println)
        }
    }
}
