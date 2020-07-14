package com.atguigu.bigdata.spark.core.req.controller

import com.atguigu.bigdata.spark.core.req.bean.HotCategory
import com.atguigu.bigdata.spark.core.req.service.{HotCategorySessionTop10Service, HotCategoryTop10Service}
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-09 14:02
 */
class HotCategorySessionTop10Controller extends TController{

    private val hotCategoryTop10Service = new HotCategoryTop10Service
    private val hotCategorySessionTop10Service = new HotCategorySessionTop10Service

    override def executor(): Unit = {
        val top10: List[HotCategory] = hotCategoryTop10Service.analysis()
        val result = hotCategorySessionTop10Service.analysis(top10)
        result.foreach(println)
    }
}
