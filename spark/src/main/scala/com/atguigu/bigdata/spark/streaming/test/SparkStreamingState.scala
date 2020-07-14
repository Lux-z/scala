package com.atguigu.bigdata.spark.streaming.test

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lux_zhang
 * @create 2020-06-14 18:48
 */
object SparkStreamingState {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc = new StreamingContext(sparkConf, Seconds(3))
        //设定检查点路径
        ssc.sparkContext.setCheckpointDir("in")
        val ds = ssc.socketTextStream("localhost", 9999)

        //数据有状态的保存 将spark每个采集周期数据的处理结果保存起来，然后和后续的数据进行聚合
        //有状态的目的其实就是将每一个采集周期数据的计算结果临时保存起来,在下一次数据的处理中继续使用
        ds.flatMap(_.split(" ")).
                map((_,1L)).
                // reduceByKey方法是无状态的 使用updateStateByKey()替换
                // updateStateByKey 是有状态计算方法
                // 第一个参数表示 相同key的value的集合
                // 第二个参数表示 想用key的缓冲区的数据,有可能为空
                updateStateByKey[Long](
                    (seq:Seq[Long],buffer:Option[Long])=> {
                        val newBufferValue = buffer.getOrElse(0L) + seq.sum
                        Option(newBufferValue)
                    }
                )
                print()

        ssc.start()
        ssc.awaitTermination()
    }
}
