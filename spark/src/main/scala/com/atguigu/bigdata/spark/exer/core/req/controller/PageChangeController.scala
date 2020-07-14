package com.atguigu.bigdata.spark.exer.core.req.controller

import com.atguigu.bigdata.spark.exer.core.req.service.PageChangeService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-20 10:10
 */
class PageChangeController extends TController{
    private val pageChangeService = new PageChangeService
    override def executor(): Unit = {
        val result = pageChangeService.analysis()
    }
}
