package com.atguigu.bigdata.spark.core.req.dao

import com.atguigu.summer.framework.core.TDao
import com.atguigu.summer.framework.util.EnvUtil
import org.apache.spark.rdd.RDD


/**
 * @author lux_zhang
 * @create 2020-06-09 14:05
 */
class HotCategoryTop10Dao extends TDao{
    override def readFile(path: String): RDD[String] = EnvUtil.getEnv().textFile(path)
}
