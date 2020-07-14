package com.atguigu.bigdata.spark.exer.core.req.dao

import com.atguigu.bigdata.spark.core.req.bean.UserVisitAction
import com.atguigu.summer.framework.core.TDao
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-19 22:06
 */
class Top10SessionDao extends TDao{
    def getUserVisitAction(path:String) = {
        val rdd: RDD[String] = readFile(path)
        rdd.map(
            line=>{
                val datas: Array[String] = line.split("_")
                UserVisitAction(
                    datas(0),
                    datas(1).toLong,
                    datas(2),
                    datas(3).toLong,
                    datas(4),
                    datas(5),
                    datas(6).toLong,
                    datas(7).toLong,
                    datas(8),
                    datas(9),
                    datas(10),
                    datas(11),
                    datas(12).toLong
                )
            }
        )
    }
}
