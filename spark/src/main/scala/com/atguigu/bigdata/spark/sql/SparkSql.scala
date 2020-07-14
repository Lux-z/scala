package com.atguigu.bigdata.spark.sql

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-10 16:33
 */
object SparkSql {
    def main(args: Array[String]): Unit = {
        //创建环境对象
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark-Sql")
        val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
        //导入隐式转换，这里的spark其实是环境对象的名称 要求对象必须使用val声明
        import spark.implicits._
        //逻辑操作
        val df: DataFrame = spark.read.json("input/user.json")
        //SQL
        df.createOrReplaceTempView("user")
        spark.sql("select * from user").show()

        //DSL
        df.select("username","age").show
        //如果要用单引号引用查询列名，则需要隐式转换 需要导入
        df.select('username,'age).show()

        //RDD <=> DataFrame
        val rdd = spark.sparkContext.makeRDD(List((1,"zhangsan",30),(2,"lisi",40),(3,"wangwu",20)))
        val rdf: DataFrame = rdd.toDF("id","name","age")
        rdf.show()

        val dfToRdd: RDD[Row] = rdf.rdd
        dfToRdd.foreach(println)

        //RDD <=> DataSet
        val userAdd: RDD[User] = rdd.map{case (id,name,age)=>User(id,name,age)}
        val userDs: Dataset[User] = userAdd.toDS()

        userDs.show()

        userDs.rdd
        //DataFrame <=> DataSet
        rdf.as[User]

        userDs.toDF()
        //释放对象
        spark.stop()
    }
    case class User(id:Int,name:String,age:Int)
}
