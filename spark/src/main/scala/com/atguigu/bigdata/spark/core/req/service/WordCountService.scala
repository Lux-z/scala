package com.atguigu.bigdata.spark.core.req.service

import com.atguigu.bigdata.spark.core.req.dao.WordCountDao
import com.atguigu.summer.framework.core.{TController, TService}
import com.atguigu.summer.framework.util.EnvUtil
import org.apache.spark.SparkContext

/**
 * @author lux_zhang
 * @create 2020-06-08 21:05
 */
class WordCountService extends TService{
    private val dao = new WordCountDao
    override def analysis() = {
        val wordCountArray: Array[(String, Int)] = dao.readFile("input11/data.txt").
                flatMap(_.split(" ")).
                map((_, 1)).reduceByKey(_ + _).
                collect()
        wordCountArray
    }
}
