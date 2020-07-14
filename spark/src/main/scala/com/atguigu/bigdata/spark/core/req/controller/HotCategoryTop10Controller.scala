package com.atguigu.bigdata.spark.core.req.controller

import com.atguigu.bigdata.spark.core.req.service.HotCategoryTop10Service
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-09 14:02
 */
class HotCategoryTop10Controller extends TController{
    private val newHotCategoryTop10Service = new HotCategoryTop10Service
    override def executor(): Unit = {
        val result = newHotCategoryTop10Service.analysis()
        result.foreach(println)

    }
}
