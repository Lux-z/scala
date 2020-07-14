package com.atguigu.bigdata.spark.streaming.test

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lux_zhang
 * @create 2020-06-14 18:48
 */
object SparkStreamingTransform {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc = new StreamingContext(sparkConf, Seconds(3))

        val ds: ReceiverInputDStream[String] = ssc.socketTextStream("localhost",9999)
        //transform  对DStream中的RDD应用转换
        ds.transform(rdd=>{
            println("******")
            rdd.map(_ * 2)
        }).print()

        ssc.start()
        ssc.awaitTermination()
    }
}
