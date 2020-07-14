package com.atguigu.bigdata.spark.core.rdd.scala05.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-07 18:19
 */
object RddJoin {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File - RDD"))
        val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a",1), ("b", 2),("a",3)))
        val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("a",6), ("b", 5),("a",4)))

        //join方法可以将两个rdd中相同的key的value连接在一起 key可重复
        //join方法性能不太高(数据产生笛卡尔积)，能不用尽量不要用
        rdd1.join(rdd2).collect().foreach(println)
        //leftOuterJoin 类似于SQL语句的左外连接
        //rightOuterJoin 右外

        //cogroup 两次连接，rdd内部相同key连接后再和外部rdd相同key连接

    }
}
