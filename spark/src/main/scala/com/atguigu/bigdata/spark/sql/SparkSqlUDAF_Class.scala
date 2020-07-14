package com.atguigu.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, Row, SparkSession}

/**
 * @author lux_zhang
 * @create 2020-06-12 11:21
 */
object SparkSqlUDAF_Class {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("spark-udaf")
        val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
        import spark.implicits._
        val rdd = spark.sparkContext.makeRDD(List(
            (1, "zhangsan", 30L),
            (2, "lisi", 20L),
            (3, "wangwu", 40L)
        ))

        val df: DataFrame = rdd.toDF("id","name","age")
        df.createOrReplaceTempView("user")
        val ds: Dataset[User] = df.as[User]

        val udaf = new MyAvgUDAF

        //toColum 将聚合函数转换为查询的列让DataSet访问
        ds.select(udaf.toColumn).show()


        spark.stop()
    }

    case class User( id:Int, name:String, age:Long )
    case class AvgBuffer( var totalage:Long, var count:Long )

    //自定义聚合函数  继承 UserDefinedAggregateFunction

    class MyAvgUDAF extends Aggregator[User,AvgBuffer,Long] {
        override def zero: AvgBuffer = AvgBuffer(0L,0L)

        override def reduce(b: AvgBuffer, a: User): AvgBuffer = {
            b.totalage = b.totalage + a.age
            b.count = b.count + 1
            b
        }

        override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
            b1.totalage = b1.totalage + b2.totalage
            b1.count = b2.count + b2.count
            b1
        }

        //计算函数的结果
        override def finish(reduction: AvgBuffer): Long = {
            reduction.totalage / reduction.count
        }

        //编码方式 自定义类型固定写法 Encoders.product
        override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

        //
        override def outputEncoder: Encoder[Long] = Encoders.scalaLong
    }
}
