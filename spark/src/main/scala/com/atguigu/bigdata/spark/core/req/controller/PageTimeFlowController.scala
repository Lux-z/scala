package com.atguigu.bigdata.spark.core.req.controller

import com.atguigu.bigdata.spark.core.req.service.PageTimeFlowService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-10 10:02
 */
class PageTimeFlowController extends TController{
    private val service = new PageTimeFlowService

    override def executor(): Unit = {
        service.analysis()
    }
}
