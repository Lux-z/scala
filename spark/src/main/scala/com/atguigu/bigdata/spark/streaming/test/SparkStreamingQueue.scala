package com.atguigu.bigdata.spark.streaming.test

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-06-14 18:48
 */
object SparkStreamingQueue {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc = new StreamingContext(sparkConf, Seconds(3))

        //执行逻辑
        val queue = new mutable.Queue[RDD[String]]()
        val queueDS: InputDStream[String] = ssc.queueStream(queue)

        queueDS.print()

        ssc.start()

        for (i<- 1 to 5) {
            val rdd = ssc.sparkContext.makeRDD(List(i.toString))
            queue.enqueue(rdd)
            Thread.sleep(1000)
        }
        ssc.awaitTermination()
    }
}
