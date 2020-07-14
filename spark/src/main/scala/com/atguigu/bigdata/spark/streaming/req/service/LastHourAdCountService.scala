package com.atguigu.bigdata.spark.streaming.req.service

import com.atguigu.bigdata.spark.streaming.req.bean.Click_Log
import com.atguigu.bigdata.spark.streaming.req.dao.LastHourAdCountDao
import com.atguigu.summer.framework.core.TService
import org.apache.spark.streaming.{Minutes, Seconds}
import org.apache.spark.streaming.dstream.DStream

/**
 * 统计最近一小时广告点击量
 * @author lux_zhang
 * @create 2020-06-19 11:00
 */
class LastHourAdCountService extends TService {
    private val lastHourAdCountDao = new LastHourAdCountDao
    override def analysis(): Any = {
        val kafkaDS: DStream[String] = lastHourAdCountDao.readKafka()
        //封装
        val click_DS: DStream[Click_Log] = kafkaDS.map(log => {
            val datas: Array[String] = log.split(" ")
            Click_Log(datas(0).toLong, datas(1), datas(2), datas(3), datas(4))
        })

        val mapDS: DStream[((String, Long), Int)] = click_DS.map(ds=> ((ds.adid, ds.ts / 10000 * 10000),1))
        //有状态的转化
        val reduceDS: DStream[((String, Long), Int)] = mapDS.reduceByKeyAndWindow((a:Int, b:Int) =>
            a + b, Minutes(1), Seconds(10)
        )
        //将数据格式转化，通过广告id进行分组
        val groupDS: DStream[(String, Iterable[(Long, Int)])] = reduceDS.map {
            case ((adid, ts), sum) => (adid, (ts, sum))
        }.groupByKey()

        //将分组后的数据进行时间排序，显示数据
        groupDS.mapValues(iter=>{
            iter.toList.sortWith((left,right)=>left._1 < right._1)
        }).print()
    }
}
