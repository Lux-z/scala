package com.atguigu.bigdata.spark.streaming.req.application

import com.atguigu.bigdata.spark.streaming.req.controller.MokeDataController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-18 22:32
 */
object MockDataApplication extends App with TApplication{
    start("sparkStreaming") {
        val controller = new MokeDataController
        controller.executor()
    }
}
