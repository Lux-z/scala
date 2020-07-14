package com.atguigu.bigdata.spark.exer.core.req.controller

import com.atguigu.bigdata.spark.exer.core.req.service.HotCategoryTop10Service
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-19 20:10
 */
class HotCategoryTop10Controller extends TController{
    private val hotCategoryTop10Service = new HotCategoryTop10Service
    override def executor(): Unit = {
        val result = hotCategoryTop10Service.analysis()
        result.foreach(println)

    }
}
