package com.atguigu.bigdata.spark.streaming.req.controller

import com.atguigu.bigdata.spark.streaming.req.service.MokeDataService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-18 22:33
 */
class MokeDataController extends TController{
    private val mokeDataService = new MokeDataService
    override def executor(): Unit = {
        val result: Any = mokeDataService.analysis()
    }
}
