package com.atguigu.bigdata.spark.core.req.application

import com.atguigu.bigdata.spark.core.req.controller.{HotCategorySessionTop10Controller, HotCategoryTop10Controller}
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-09 14:01
 */
object HotCategorySessionTop10App extends App with TApplication {

    start("spark") {
        val controller = new HotCategorySessionTop10Controller
        controller.executor()
    }
}
