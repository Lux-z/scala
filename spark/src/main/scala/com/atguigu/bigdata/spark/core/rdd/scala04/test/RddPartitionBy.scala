package com.atguigu.bigdata.spark.core.rdd.scala04.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-07 14:20
 */
object RddPartitionBy {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-Rdd")
        val sc = new SparkContext(conf)
        val dataRdd: RDD[(String, String)] = sc.makeRDD(List(
            ("cba", "消息1"), ("cba", "消息2"), ("cba", "消息3"),
            ("nba", "消息4"), ("wnba", "消息5"), ("nba", "消息6")
        ), 1)
        val rdd: RDD[(String, String)] = dataRdd.partitionBy(new myPartition(3))
        rdd.mapPartitionsWithIndex((index,datas)=> datas.map((index,_))).collect().foreach(println)

        sc.stop()
    }

    class myPartition(num:Int) extends Partitioner {
        //获取分区的数量
        override def numPartitions: Int = {
            num
        }

        // 根据数据的key来决定数据在哪个分区中进行处理
        // 方法的返回值表示分区编号（索引）
        override def getPartition(key: Any): Int = {
            key match {
                case "nba" => 0
                case _ => 1
            }
        }
    }

}
