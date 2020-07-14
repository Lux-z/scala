package com.atguigu.bigdata.spark.streaming.req.service

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.text.SimpleDateFormat

import com.atguigu.bigdata.spark.streaming.req.bean.Click_Log
import com.atguigu.bigdata.spark.streaming.req.dao.BlackListDao
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.JDBCUtil
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream

import scala.collection.mutable.ListBuffer

/**
 * @author lux_zhang
 * @create 2020-06-18 22:44
 */
class BlackListService extends TService{
    private val blackListDao = new BlackListDao
    override def analysis(): Any = {
        //获取kafka的数据
        val kafkaDs: DStream[String] = blackListDao.readKafka()

        val clickDs: DStream[Click_Log] = kafkaDs.map(log => {
            val logs: Array[String] = log.split(" ")

            Click_Log(logs(0).toLong, logs(1), logs(2), logs(3), logs(4))
        })

        //周期性的判断用户id是否在黑名单中，如果存在则过滤

        val reduceDS: DStream[((String, String, String), Int)] = clickDs.transform(rdd => {
            //通过JDBC获取黑名单中的数据
            val connection: Connection = JDBCUtil.getConnection()
            val pstat: PreparedStatement = connection.prepareStatement(
                """
                  |select userid from black_list
                  |""".stripMargin)
            val set: ResultSet = pstat.executeQuery()
            val blackBuffer = new ListBuffer[String]
            while (set.next()) {
                blackBuffer.append(set.getString(1))
            }
            set.close
            pstat.close
            connection.close()
            //过滤黑名单中的用户信息
            val filterRDD: RDD[Click_Log] = rdd.filter(logs => !blackBuffer.contains(logs.userid))

            //map + reduceByKey
            val sd = new SimpleDateFormat("yyyy-MM-dd")
            filterRDD.map(log => ((sd.format(log.ts), log.userid, log.adid), 1)).reduceByKey(_ + _)
        })

        //将统计的结果中超过阈值的用户信息拉人到黑名单中
        reduceDS.foreachRDD(rdd=>{
            rdd.foreach{
                case ((date,userid,adid),sum) => {
                    val conn: Connection = JDBCUtil.getConnection()
                    val stat: PreparedStatement = conn.prepareStatement(
                        """
                          |insert into user_ad_count(dt,userid,adid,count)
                          |values (?,?,?,?)
                          |on duplicate key
                          |update count = count + ?
                          |""".stripMargin)
                    stat.setString(1,date)
                    stat.setString(2,userid)
                    stat.setString(3,adid)
                    stat.setLong(4,sum)
                    stat.setLong(5,sum)
                    stat.executeUpdate()

                    //获取最新的用户统计数据 判断是否超过黑名单阈值，如果超过加入到黑名单中
                    val stat1: PreparedStatement = conn.prepareStatement(
                        """
                          |insert into black_list (userid)
                          |select userid from user_ad_count
                          | where dt = ? and userid = ? and adid = ? and count >= 100
                          |on duplicate key
                          |update userid = ?
                          |""".stripMargin)
                    stat1.setString(1,date)
                    stat1.setString(2,userid)
                    stat1.setString(3,adid)
                    stat1.setString(4,userid)
                    stat1.executeUpdate()

                    stat1.close()
                    stat.close()
                    conn.close()
                }
            }
        })
       
        




    }
}
