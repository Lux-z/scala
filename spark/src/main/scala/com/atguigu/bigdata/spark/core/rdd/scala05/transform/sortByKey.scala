package com.atguigu.bigdata.spark.core.rdd.scala05.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-07 18:10
 */
object sortByKey {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("File-Rdd"))
        val dataRdd: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("c", 3),("b",2)))
        //dataRdd.sortByKey(true).collect().foreach(println)

        //小功能：设置key为自定义类User
        // 如果自定义key进行排序，需要将key混入特质Ordered
        sc.makeRDD(List(
            ( new User(), 1 ),
            ( new User(), 2 ),
            ( new User(), 3 ),
        )).collect().foreach(println)

        sc.stop()
    }

    class User extends Ordered[User] with Serializable {
        override def compare(that: User): Int = {
            1
        }
    }
}
