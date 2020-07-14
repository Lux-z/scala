package com.atguigu.bigdata.spark.streaming.test

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lux_zhang
 * @create 2020-06-14 18:48
 */
object SparkStreamingFile {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc = new StreamingContext(sparkConf, Seconds(3))

        //执行逻辑  监控文件夹  只能监控启动后文件夹新创建的文件
        val dir: DStream[String] = ssc.textFileStream("in")
        dir.print()


        ssc.start()

        ssc.awaitTermination()
    }
}
