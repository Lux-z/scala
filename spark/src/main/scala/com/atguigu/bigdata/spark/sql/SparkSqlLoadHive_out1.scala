package com.atguigu.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, MapType, StringType, StructField, StructType}

/**
 * @author lux_zhang
 * @create 2020-06-12 20:40
 */
object SparkSqlLoadHive_out1 {
    def main(args: Array[String]): Unit = {
        System.setProperty("HADOOP_USER_NAME", "atguigu")
        val conf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")

        val spark = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
        import spark.implicits._
        
        spark.sql("use my_data")

        //获取满足条件的数据
        spark.sql(
            """
              |select uva.*,c.area,p.product_name,c.city_name
              |from user_visit_action as uva
              |join city_info as c on uva.city_id = c.city_id
              |join product_info as p on uva.click_product_id = p.product_id
              |where uva.click_product_id > -1
              |""".stripMargin).createOrReplaceTempView("t1")

        //将数据根据区域和商品进行分组，统计商品点击的数量 使用自定义聚合函数
        val udaf = new cityProportion
        //注册聚合函数
        spark.udf.register("cityRemark",udaf)

        spark.sql(
            """
              |select area,product_name,count(*) as clickCount,cityRemark(city_name)
              |from t1 group by area,product_name
              |""".stripMargin).createOrReplaceTempView("t2")

        //将统计结果根据数量进行排序（降序）
        spark.sql(
            """
              |select *,rank() over(partition by area order by clickCount desc) as rank
              |from t2
              |""".stripMargin).createOrReplaceTempView("t3")

        //将排序后的数据取前三名
        spark.sql(
            """
              |select *
              |from t3
              |where rank <= 3
              |""".stripMargin).show()
        spark.stop()
    }
    // 自定义城市备注聚合函数
    class cityProportion extends UserDefinedAggregateFunction {
        //输入城市名
        override def inputSchema: StructType = {
            StructType(Array(StructField("cityName",StringType)))
        }

        //缓冲区中的数据应该为：totalcnt, Map[cityname, cnt]
        override def bufferSchema: StructType = {
            StructType(
                Array(
                    StructField("totalCount",LongType),
                    StructField("cityMap",MapType(StringType,LongType)
                    )
                )
            )
        }

        //返回城市备注的字符串类型
        override def dataType: DataType = {
            StringType
        }

        //函数稳定性
        override def deterministic: Boolean = true

        //缓冲区的初始化
        override def initialize(buffer: MutableAggregationBuffer): Unit = {
            buffer(0) = 0L
            buffer(1) = Map[String,Long]()
        }

        //更新缓冲区
        override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
            val cityName: String = input.getString(0)
            buffer(0) = buffer.getLong(0) + 1
            val cityMap: Map[String, Long] = buffer.getAs[Map[String,Long]](1)
            val newCityClick: Long = cityMap.getOrElse(cityName,0L) + 1

            buffer(1) = cityMap.updated(cityName,newCityClick)
        }

        //合并缓冲区
        override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
            //合并点击数
            buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
            // 合并城市点击map
            val map1: Map[String, Long] = buffer1.getAs[Map[String, Long]](1)
            val map2: Map[String, Long] = buffer2.getAs[Map[String, Long]](1)
            buffer1(1) = map1.foldLeft(map2) {
                case (map,(k,v))=>{
                    map.updated(k,map.getOrElse(k,0L) + v)
                }
            }
        }

        //对缓冲区进行计算并返回备注信息
        override def evaluate(buffer: Row): Any = {
            val totalCount: Long = buffer.getLong(0)
            val cityMap: Map[String, Long] = buffer.getAs[Map[String, Long]](1)
            val cityCount: List[(String, Long)] = cityMap.toList.sortWith((left,right)=>left._2 > right._2).take(2)

            val builder = new StringBuilder
            var rest = 0L
            cityCount.foreach{
                case (city,count)=>{
                    val r: Long = count * 100 / totalCount
                    builder.append(s"$city $r%,")
                    rest = rest + r
                }
            }
            builder.toString() + s"其他：${100 - rest}%"
        }

    }

}
