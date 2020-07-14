package com.atguigu.bigdata.spark.exer.core.req.application

import com.atguigu.bigdata.spark.exer.core.req.controller.HotCategoryTop10Controller
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-19 20:07
 */
object HotCategoryTop10Application extends App with TApplication {
    start("spark") {
        val hotCategoryTop10Controller = new HotCategoryTop10Controller
        hotCategoryTop10Controller.executor()
    }
}
