package com.atguigu.bigdata.spark.streaming.req.application

import com.atguigu.bigdata.spark.streaming.req.controller.Ad_CountController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-19 9:48
 */
object Ad_CountApplication extends App with TApplication{
    start("sparkStreaming") {
        val controller = new Ad_CountController
        controller.executor()
    }
}
