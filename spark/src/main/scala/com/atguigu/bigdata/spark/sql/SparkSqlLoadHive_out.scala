package com.atguigu.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author lux_zhang
 * @create 2020-06-12 20:40
 */
object SparkSqlLoadHive_out {
    def main(args: Array[String]): Unit = {
        System.setProperty("HADOOP_USER_NAME", "atguigu")
        val conf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        //访问外置的Hive 需要将hive-site.xml拷贝到项目resource目录下
        val spark = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

//        spark.sql("create table user(id int,name string)")
        
        spark.sql("use my_data")

        spark.sql(
            """
              |CREATE TABLE `user_visit_action`(
              |  `date` string,
              |  `user_id` bigint,
              |  `session_id` string,
              |  `page_id` bigint,
              |  `action_time` string,
              |  `search_keyword` string,
              |  `click_category_id` bigint,
              |  `click_product_id` bigint,
              |  `order_category_ids` string,
              |  `order_product_ids` string,
              |  `pay_category_ids` string,
              |  `pay_product_ids` string,
              |  `city_id` bigint)
              |row format delimited fields terminated by '\t'
            """.stripMargin)

        spark.sql(
            """
              |load data local inpath 'input/user_visit_action.txt' into table my_data.user_visit_action
            """.stripMargin)

        spark.sql(
            """
              |CREATE TABLE `product_info`(
              |  `product_id` bigint,
              |  `product_name` string,
              |  `extend_info` string)
              |row format delimited fields terminated by '\t'
            """.stripMargin)

        spark.sql(
            """
              |load data local inpath 'input/product_info.txt' into table my_data.product_info
            """.stripMargin)

        spark.sql(
            """
              |CREATE TABLE `city_info`(
              |  `city_id` bigint,
              |  `city_name` string,
              |  `area` string)
              |row format delimited fields terminated by '\t'
            """.stripMargin)

        spark.sql(
            """
              |load data local inpath 'input/city_info.txt' into table my_data.city_info
            """.stripMargin)

        spark.sql("show tables").show()
        spark.stop()
    }
}
