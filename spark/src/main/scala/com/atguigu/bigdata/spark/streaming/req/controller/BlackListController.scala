package com.atguigu.bigdata.spark.streaming.req.controller

import com.atguigu.bigdata.spark.streaming.req.service.BlackListService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-18 22:44
 */
class BlackListController extends TController{
    private val blackListService = new BlackListService
    override def executor(): Unit = {
        blackListService.analysis()
    }
}
