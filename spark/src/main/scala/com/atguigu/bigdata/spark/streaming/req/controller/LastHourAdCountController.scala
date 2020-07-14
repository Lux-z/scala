package com.atguigu.bigdata.spark.streaming.req.controller

import com.atguigu.bigdata.spark.streaming.req.service.LastHourAdCountService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-19 10:59
 */
class LastHourAdCountController extends TController{
    private val hourAdCountService = new LastHourAdCountService
    override def executor(): Unit = {
        hourAdCountService.analysis()
    }
}
