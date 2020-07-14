package com.atguigu.bigdata.spark.test.rdd

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * 	需求：循环创建几个RDD，将RDD放入队列。通过SparkStream创建Dstream，计算WordCount
 * @author lux_zhang
 * @create 2020-07-14 18:56
 */
object SparkStreaming {
    def main(args: Array[String]): Unit = {
        //1.初始化Spark的配置信息
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")

        //2.初始化SparkStreamingContext
        val context = new StreamingContext(conf,Seconds(3))

        //3.创建RDD队列
        val rddQueue = new mutable.Queue[RDD[Int]]()

        //4.创建QueueInputDStream
        val inputStream: InputDStream[Int] = context.queueStream(rddQueue)

        //5.处理队列中的RDD数据
        val result: DStream[(Int, Int)] = inputStream.map((_,1)).reduceByKey(_+_)

        //6.打印结果
        result.print()

        //7.启动任务
        context.start()

        //8.循环创建并向RDD队列中放入RDD
        for(i <- 1 to 5) {
            rddQueue += context.sparkContext.makeRDD(1 to 300,10)
            Thread.sleep(1000)
        }

        context.awaitTermination()
    }
}
