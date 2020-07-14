package com.atguigu.bigdata.spark.streaming.test

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lux_zhang
 * @create 2020-06-13 11:41
 */
object SparkStringWordCount {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc = new StreamingContext(conf,Seconds(3))

        //执行逻辑
        val socketDS: ReceiverInputDStream[String] = ssc.socketTextStream("localhost",9999)

        val value: DStream[(String, Int)] = socketDS.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
        value.print()

        //启动采集器
        ssc.start()
        //等待采集器的结束
        ssc.awaitTermination()
    }
}
