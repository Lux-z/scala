package com.atguigu.bigdata.spark.streaming.test

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lux_zhang
 * @create 2020-06-14 18:48
 */
object SparkStreamingWindow {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc = new StreamingContext(sparkConf, Seconds(3))
        //设定检查点路径
        ssc.sparkContext.setCheckpointDir("in")
        val ds = ssc.socketTextStream("localhost", 9999)
        val wordToDS: DStream[(String, Int)] = ds.flatMap(_.split(" ")).map((_,1))
        //聚合多个采集周期的数据  将多个采集周期数作为计算的整体
        //参数1 窗口的范围           应该设置为采集周期的整数倍
        //参数2 滑动的幅度（步长）   默认为一个采集周期      滑动步长设置为采集周期的整数倍
        //窗口计算周期等同于窗口滑动的步长
        wordToDS.window(Seconds(9)).reduceByKey(_ + _).print()

        ssc.start()
        ssc.awaitTermination()
    }
}
