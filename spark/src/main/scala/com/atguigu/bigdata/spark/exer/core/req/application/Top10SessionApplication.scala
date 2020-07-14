package com.atguigu.bigdata.spark.exer.core.req.application

import com.atguigu.bigdata.spark.exer.core.req.controller.Top10SessionController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-19 22:04
 */
object Top10SessionApplication extends App with TApplication{
    start("spark") {
        val controller = new Top10SessionController
        controller.executor()
    }
}
