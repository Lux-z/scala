package com.atguigu.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author lux_zhang
 * @create 2020-06-12 20:40
 */
object SparkSqlLoadMySql {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark = SparkSession.builder().config(conf).getOrCreate()
        import spark.implicits._
        //方式1：通用的load方法读取
        spark.read.format("jdbc")
                .option("url", "jdbc:mysql://hadoop72:3306/spark-sql")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("user", "root")
                .option("password", "123456")
                .option("dbtable", "help_category")
                .load().show



        spark.stop()
    }
}
