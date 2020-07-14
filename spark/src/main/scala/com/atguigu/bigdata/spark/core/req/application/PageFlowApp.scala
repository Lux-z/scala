package com.atguigu.bigdata.spark.core.req.application

import com.atguigu.bigdata.spark.core.req.controller.PageFlowController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-10 10:01
 */
object PageFlowApp extends App with TApplication{
    start("spark") {
        val controller = new PageFlowController
        controller.executor()
    }
}
