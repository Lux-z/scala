package com.atguigu.bigdata.spark.streaming.req.service

import com.atguigu.bigdata.spark.streaming.req.dao.MokeDataDao
import com.atguigu.summer.framework.core.TService

/**
 * @author lux_zhang
 * @create 2020-06-18 22:33
 */
class MokeDataService extends TService{
    private val mokeDataDao = new MokeDataDao
    override def analysis(): Any = {
        val datas: Seq[String] = mokeDataDao.genMockData()

        mokeDataDao.writeToKakfa(datas)
    }
}
