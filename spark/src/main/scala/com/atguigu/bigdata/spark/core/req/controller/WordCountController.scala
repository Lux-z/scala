package com.atguigu.bigdata.spark.core.req.controller

import com.atguigu.bigdata.spark.core.req.service.WordCountService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-08 21:04
 */
class WordCountController extends TController{
    private val service = new WordCountService
    override def executor(): Unit = {
        val value: Array[(String, Int)] = service.analysis()
        value.foreach(println)
    }
}
