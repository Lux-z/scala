package com.atguigu.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author lux_zhang
 * @create 2020-06-12 20:40
 */
object SparkSqlLoadSave {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark = SparkSession.builder().config(conf).getOrCreate()
        import spark.implicits._

        //SparkSQL通用读取的默认数据格式为Parquet列式存储格式
//        spark.read.load("input/users.parquet").show()

        //如果想要改变读取文件的格式  format()
        val df: DataFrame = spark.read.format("json").load("input/user.json")
//        df.show()

        //我们也可以直接在文件上进行查询:  文件格式.`文件路径`
//        spark.sql("select * from json.`input/user.json`").show

        //通用的保存 sparksql默认通用保存的文件格式为parquet
        //如果想要保存的格式是指定的格式，比如json，那么需要进行对应的格式化操作
//        df.write.format("json").save("output")
        //如果要在已经存在路径下保存数据，那么可以使用保存模式 mode() overwrite 覆盖 append 追加
        df.write.mode("append").format("json").save("output")



        spark.stop()
    }
}
