package com.atguigu.bigdata.spark.core.req.service

import com.atguigu.bigdata.spark.core.req.bean.HotCategory
import com.atguigu.bigdata.spark.core.req.dao.HotCategoryTop10Dao
import com.atguigu.bigdata.spark.core.req.helper.Top10Accumulator
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.EnvUtil
import org.apache.spark.rdd.RDD

import scala.collection.mutable
import scala.collection.mutable.ArrayOps

/**
 * @author lux_zhang
 * @create 2020-06-09 14:04
 */
class HotCategoryTop10Service extends TService{
    val newHotCategoryTop10Dao = new HotCategoryTop10Dao

    override def analysis() = {
        val actionRdd = newHotCategoryTop10Dao.readFile("input11/user_visit_action.txt")

        //声明，使用累加器
        val acc = new Top10Accumulator
        EnvUtil.getEnv().register(acc)

        actionRdd.foreach{
            line=> {
                val datas: Array[String] = line.split("_")
                if (datas(6) != "-1") acc.add(datas(6), "click")
                else if (datas(8) != "null") datas(8).split(",").foreach(acc.add(_, "order"))
                else if (datas(10) != "null") datas(10).split(",").foreach(acc.add(_, "pay"))
                else Nil
            }
        }
        //获取累加器的值
        val value: mutable.Map[String, HotCategory] = acc.value

        //map + sort
        value.map(_._2).toList.sortWith{
            (le, ri) => {
                if (le.clickCount > ri.clickCount)
                    true
                else if (le.clickCount == ri.clickCount) {
                    if (le.orderCount > ri.orderCount)
                        true
                    else if(le.orderCount == ri.orderCount) {
                        le.payCount > ri.payCount
                    } else false
                }
                else false
            }
        }.take(10)
    }

    def analysis2() = {

        val actionRdd = newHotCategoryTop10Dao.readFile("input/user_visit_action.txt")
        val flatMapRdd: RDD[(String, HotCategory)] = actionRdd.flatMap { action =>
            val datas: ArrayOps.ofRef[String] = action.split("_")
            if (datas(6) != "-1") {
                List((datas(6), HotCategory(datas(6), 1, 0, 0)))
            } else if (datas(8) != "null") {
                datas(8).split(",").map(id=>(id, HotCategory(id, 0, 1, 0)))
            } else if (datas(10) != "null") {
                datas(10).split(",").map(id=>(id, HotCategory(id, 0, 0, 1)))
            } else {
                Nil
            }
        }
        flatMapRdd.reduceByKey(
            (h1, h2) => {
                h1.clickCount += h2.clickCount
                h1.orderCount += h2.orderCount
                h1.payCount += h2.payCount
                h1
            }
        ).collect().sortWith((left, right) => {
            val le: HotCategory = left._2
            val ri: HotCategory = right._2
            if (le.clickCount > ri.clickCount)
                true
            else if (le.clickCount == ri.clickCount) {
                if (le.orderCount > ri.orderCount)
                    true
                else if(le.orderCount == ri.orderCount) {
                    le.payCount > ri.payCount
                } else false
            }
            else false
        }).take(10)
    }

        def analysis1() = {

        val actionRdd = newHotCategoryTop10Dao.readFile("input/user_visit_action.txt")
        val flatMapRdd: RDD[(String, (Int, Int, Int))] = actionRdd.flatMap { action =>
            val datas: ArrayOps.ofRef[String] = action.split("_")
            if (datas(6) != "-1") {
                List((datas(6), (1, 0, 0)))
            } else if (datas(8) != "null") {
                datas(8).split(",").map((_, (0, 1, 0)))
            } else if (datas(10) != "null") {
                datas(10).split(",").map((_, (0, 0, 1)))
            } else {
                Nil
            }
        }
        flatMapRdd.reduceByKey(
            (t1,t2)=> (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
        ).sortBy(_._2, false).take(10)


    }

}
