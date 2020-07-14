package com.atguigu.bigdata.spark.streaming.req.application

import com.atguigu.bigdata.spark.streaming.req.controller.LastHourAdCountController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-19 10:59
 */
object LastHourAdCountApplication extends App with TApplication{
    start("sparkStreaming") {
        val controller = new LastHourAdCountController
        controller.executor()
    }
}
