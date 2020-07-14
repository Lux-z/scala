package com.atguigu.bigdata.spark.core.req.controller

import com.atguigu.bigdata.spark.core.req.service.PageFlowService
import com.atguigu.summer.framework.core.TController

/**
 * @author lux_zhang
 * @create 2020-06-10 10:02
 */
class PageFlowController extends TController{
    private val service = new PageFlowService

    override def executor(): Unit = {
        service.analysis()
    }
}
