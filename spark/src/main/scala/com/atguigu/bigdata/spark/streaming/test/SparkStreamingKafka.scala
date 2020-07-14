package com.atguigu.bigdata.spark.streaming.test

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author lux_zhang
 * @create 2020-06-14 18:48
 */
object SparkStreamingKafka {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
        val ssc = new StreamingContext(sparkConf, Seconds(3))

        //使用SparkStreaming读取Kafka的数据
        //定义Kafka参数
        val kafkaPara: Map[String, Object] = Map[String, Object](
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop72:9092,hadoop73:9092,hadoop74:9092",
            ConsumerConfig.GROUP_ID_CONFIG -> "atguigu",
            "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
            "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
        )
        //读取Kafka数据创建DStream
        val kafkaDStream: InputDStream[ConsumerRecord[String, String]] =
            KafkaUtils.createDirectStream[String, String](ssc,
                LocationStrategies.PreferConsistent,
                ConsumerStrategies.Subscribe[String, String](Set("atguigu"), kafkaPara))

        //将每条消息的KV取出
        val ds: DStream[String] = kafkaDStream.map(_.value())

        //逻辑计算
        ds.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

        ssc.start()

        ssc.awaitTermination()
    }
}
