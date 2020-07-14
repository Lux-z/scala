package com.atguigu.bigdata.spark.streaming.req.controller

import com.atguigu.bigdata.spark.streaming.req.service.Ad_CountService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-19 9:48
 */
class Ad_CountController extends TController{

    private val ad_CountService = new Ad_CountService
    override def executor(): Unit = {
        ad_CountService.analysis()
    }
}
