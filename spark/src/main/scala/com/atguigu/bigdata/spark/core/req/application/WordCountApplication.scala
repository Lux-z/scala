package com.atguigu.bigdata.spark.core.req.application

import com.atguigu.bigdata.spark.core.req.controller.WordCountController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author lux_zhang
 * @create 2020-06-08 16:41
 */
object WordCountApplication extends App with TApplication{
    start("spark")  {
        val controller = new WordCountController
        controller.executor()
    }
}
