package com.atguigu.bigdata.spark.streaming.req.application

import com.atguigu.bigdata.spark.streaming.req.controller.BlackListController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-18 22:43
 */
object BlackListApplication extends App with TApplication {
    start("sparkStreaming") {
        val blackListController = new BlackListController
        blackListController.executor()
    }
}
