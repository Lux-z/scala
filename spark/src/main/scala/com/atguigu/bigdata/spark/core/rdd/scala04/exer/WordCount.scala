package com.atguigu.bigdata.spark.core.rdd.scala04.exer

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-05 19:37
 */
object WordCount {
    def main(args: Array[String]): Unit = {
        //6种不同算子的WordCount
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Rdd-Operator"))
        val dataRdd: RDD[(String)] = sc.makeRDD(List("hello", "hello spark", "hello spark scala", "hello spark scala hive"),2)
        //1.groupBy
        dataRdd.flatMap(_.split(" ")).groupBy(word=>word).map{case (word,list)=>(word,list.size)}
        //2.groupByKey
        dataRdd.flatMap(_.split(" ")).
                map(word => (word, 1)).
                groupByKey().
                map{case (word,list)=>(word,list.size)}
        //3.reduceByKey
        dataRdd.flatMap(_.split(" ")).map(word=>(word,1)).reduceByKey(_+_)
        //4.aggregateByKey
        dataRdd.flatMap(_.split(" ")).map(word=>(word,1)).aggregateByKey(0)(_+_,_+_)
        //5.foldByKey
        dataRdd.flatMap(_.split(" ")).map(word=>(word,1)).foldByKey(0)(_+_)
        //6.combineByKey
        dataRdd.flatMap(_.split(" ")).
                map(word=>(word,1)).
                combineByKey(
                    data=>data,
                    (a:Int,b)=>(a+b),
                    (c:Int,d:Int)=>(c+d)
                ).collect().foreach(println)
        //


        sc.stop()
    }
}
