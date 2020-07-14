package com.atguigu.bigdata.spark.exer.core.req.application

import com.atguigu.bigdata.spark.exer.core.req.controller.PageChangeController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-20 10:10
 */
object PageChangeApplication extends App with TApplication{
    start("spark") {
        val pageChangeController = new PageChangeController
        pageChangeController.executor()
    }
}
