package com.atguigu.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author lux_zhang
 * @create 2020-06-12 20:40
 */
object SparkSqlLoadHive {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        //SparkSQL支持本地Hive操作的，执行前需要启用Hive的支持 enableHiveSupport()
        val spark = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
        import spark.implicits._

//        spark.sql("create table user(id int,name string)")

        spark.sql("show tables").show()


        spark.stop()
    }
}
