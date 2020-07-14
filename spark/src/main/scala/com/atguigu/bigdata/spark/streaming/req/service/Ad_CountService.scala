package com.atguigu.bigdata.spark.streaming.req.service

import java.sql.{Connection, PreparedStatement}
import java.text.SimpleDateFormat

import com.atguigu.bigdata.spark.streaming.req.bean.Click_Log
import com.atguigu.bigdata.spark.streaming.req.dao.Ad_CountDao
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.JDBCUtil
import org.apache.spark.streaming.dstream.DStream

/**
 * @author lux_zhang
 * @create 2020-06-19 9:48
 */
class Ad_CountService extends TService{
    private val ad_CountDao = new Ad_CountDao
    override def analysis(): Any = {
        val kafkaDS: DStream[String] = ad_CountDao.readKafka()

        val mapDS: DStream[Click_Log] = kafkaDS.map(log => {
            val logs: Array[String] = log.split(" ")
            Click_Log(logs(0).toLong, logs(1), logs(2), logs(3), logs(4))
        })
        val dt = new SimpleDateFormat("yyyy-MM-dd")
        val clickDS: DStream[((String, String, String, String), Int)] = mapDS.map(log => {
            ((dt.format(log.ts), log.area, log.city, log.adid), 1)
        }).reduceByKey(_+_)

        val conn: Connection = JDBCUtil.getConnection()
        clickDS.foreachRDD(
            rdd=>{
                rdd.foreachPartition(datas=>{
                    val stat: PreparedStatement = conn.prepareStatement(
                        """
                          |insert into area_city_ad_count (dt,area,city,adid,count)
                          |values(?,?,?,?,?)
                          |on duplicate key
                          |update count = count + ?
                          |""".stripMargin)
                    datas.foreach{
                        case ((dt,area,city,adid),count)=>{
                            stat.setString(1,dt)
                            stat.setString(2,area)
                            stat.setString(3,city)
                            stat.setString(4,adid)
                            stat.setLong(5,count)
                            stat.setLong(6,count)
                        }
                    }

                    stat.close()
                    conn.close()
                })
            }
        )

    }
}
