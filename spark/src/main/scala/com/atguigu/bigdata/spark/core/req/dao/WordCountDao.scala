package com.atguigu.bigdata.spark.core.req.dao

import com.atguigu.summer.framework.core.TDao
import com.atguigu.summer.framework.util.EnvUtil

/**
 * @author lux_zhang
 * @create 2020-06-08 21:15
 */
class WordCountDao extends TDao{
    override def readFile(path: String) = {EnvUtil.getEnv().textFile(path)}
}
