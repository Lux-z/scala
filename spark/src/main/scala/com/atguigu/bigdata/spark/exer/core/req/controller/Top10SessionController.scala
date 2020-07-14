package com.atguigu.bigdata.spark.exer.core.req.controller

import com.atguigu.bigdata.spark.exer.core.req.service.{HotCategoryTop10Service, Top10SessionTService}
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-19 22:05
 */
class Top10SessionController extends TController{
    private val top10SessionTService = new Top10SessionTService
    private val top10 = new HotCategoryTop10Service
    override def executor(): Unit = {
        val result = top10SessionTService.analysis(top10)
        result.foreach(println)
    }
}
