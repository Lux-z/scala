package com.atguigu.bigdata.spark.core.req.service

import java.text.SimpleDateFormat

import com.atguigu.bigdata.spark.core.req.bean
import com.atguigu.bigdata.spark.core.req.dao.{PageFlowDao, PageTimeFlowDao}
import com.atguigu.summer.framework.core.TService
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-10 10:02
 */
class PageTimeFlowService extends TService{
    private val dao = new PageTimeFlowDao

    override def analysis(): Any = {
        //对指定的页面流程进行页面停留时间的统计
        val needs = List(1,2,3,4,5,6,7)
        val flowIds: List[String] = needs.zip(needs.tail).map( t => (t._1 + "-" + t._2) )
        //获取数据
        val actionAdd: RDD[bean.UserVisitAction] = dao.getUserVisitAction("input11/user_visit_action.txt")
        actionAdd.cache()

        
        //将数据按照session分组
        val sessionRdd: RDD[(String, Iterable[bean.UserVisitAction])] = actionAdd.groupBy(_.session_id)
        var format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val pageTimeRdd: RDD[(String, List[(String, Long)])] = sessionRdd.mapValues(iter => {
            val action: List[bean.UserVisitAction] = iter.toList.sortWith((left, right) => left.action_time < right.action_time)
            val tuples: List[(Long, Long)] = action.map(row => (row.page_id, format.parse(row.action_time).getTime / 1000))
            val zips: List[(String, Long)] = tuples.zip(tuples.tail).map { case (left, right) =>
                (left._1 + "-" + right._1, right._2 - left._2)
            }
            zips.filter { case (ids, times) => flowIds.contains(ids) }
        })
        val sumRdd: RDD[(String, Iterable[Long])] = pageTimeRdd.map(_._2).flatMap(t=>t).groupByKey()




        //将分组后的数据进行结构的转换,获取分子
        sumRdd.foreach{
            case (pageFlow,sum)=>
                val pageId= (pageFlow.split("-")(0).toLong)
                println("页面["+pageId+"]的停留时间为: " + (sum.sum.toDouble / sum.size))
        }

    }
}
