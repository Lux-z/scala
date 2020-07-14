package com.atguigu.bigdata.spark.exer.core.req.service

import com.atguigu.bigdata.spark.exer.core.req.bean
import com.atguigu.bigdata.spark.exer.core.req.dao.HotCategoryTop10Dao
import com.atguigu.bigdata.spark.exer.core.req.helper.Top10Accumulator
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.EnvUtil
import org.apache.spark.rdd.RDD

import scala.collection.mutable

/**
 * 分别统计每个品类点击的次数，下单的次数和支付的次数：
 * （品类，点击总数）（品类，下单总数）（品类，支付总数）
 *
 * @author lux_zhang
 * @create 2020-06-19 20:11
 */
class HotCategoryTop10Service extends TService{
    private val hotCategoryTop10Dao = new HotCategoryTop10Dao

    override def analysis()= {
        val fileRDD: RDD[String] = hotCategoryTop10Dao.readFile("input11/user_visit_action.txt")
        //声明 使用累加器
        val accumulator = new Top10Accumulator
        EnvUtil.getEnv().register(accumulator)

        fileRDD.foreach(line=>{
            val datas: Array[String] = line.split("_")

            if (datas(6) != "-1") accumulator.add((datas(6),"click"))
            else if (datas(8) != "null") datas(8).split("-").foreach(accumulator.add(_,"order"))
            else if (datas(10) != "null") datas(10).split("-").foreach(accumulator.add(_,"pay"))
            else Nil
        })

        //获取累加器的值
        val acc: mutable.Map[String, bean.HotCategory] = accumulator.value

        //排序 取Top10
        acc.map(_._2).toList.sortWith(
            (left,right)=>{
                if (left.clickCount > right.clickCount) {
                    true
                } else if (left.clickCount == right.clickCount) {
                    if (left.orderCount > right.orderCount) {
                        true
                    } else if (left.orderCount == right.orderCount) {
                        left.payCount > right.payCount
                    } else false
                }  else false
            }
        ).take(10)

    }
}
