package com.atguigu.bigdata.spark.core.req.service

import com.atguigu.bigdata.spark.core.req.bean
import com.atguigu.bigdata.spark.core.req.dao.PageFlowDao
import com.atguigu.summer.framework.core.TService
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-10 10:02
 */
class PageFlowService extends TService{
    private val dao = new PageFlowDao

    override def analysis(): Any = {
        //对指定的页面流程进行页面单跳转换率的统计
        val needs = List(1,2,3,4,5,6,7)
        val flowIds: List[String] = needs.zip(needs.tail).map( t => (t._1 + "-" + t._2) )
        //获取数据
        val actionAdd: RDD[bean.UserVisitAction] = dao.getUserVisitAction("input11/user_visit_action.txt")
        actionAdd.cache()
        //1.计算分母
        val pageCountArr: Array[(Long, Int)] = actionAdd.
                filter(action => needs.init.contains(action.page_id.toInt)).
                map(action => (action.page_id, 1)).
                reduceByKey(_ + _).collect()



        //2.计算分子(单跳转换率)
        //将数据按照session分组
        val sessionRdd: RDD[(String, Iterable[bean.UserVisitAction])] = actionAdd.groupBy(_.session_id)

        //数据处理
        val pageFlowRdd: RDD[(String, List[(String, Int)])] = sessionRdd.mapValues(iter => {
            //排序
            val action: List[bean.UserVisitAction] = iter.toList.sortWith(
                (left, right) => left.action_time < right.action_time
            )
            //将排序后的数据进行结构转换
            val pageIds: List[Long] = action.map(_.page_id)
            val zips: List[(String, Int)] = pageIds.zip(pageIds.tail).map { case (p1, p2) =>
                (p1 + "-" + p2, 1)
            }
            //过滤
            zips.filter { case (ids, count) => flowIds.contains(ids) }
        })
        //pageFlowRdd.map(_._2).flatMap(a=>a).var
        //3.将分组后的数据进行结构的转换,获取分子
        val sumRdd: RDD[(String, Int)] = pageFlowRdd.map(_._2).flatMap(data=>data).reduceByKey(_+_)

        //4.计算页面单跳转换率
        sumRdd.foreach{
            case (pageFlow,sum)=>
                val pageId= (pageFlow.split("-")(0).toLong)
                val value: Int = pageCountArr.toMap.getOrElse(pageId,1)
                println("页面跳转["+pageId+"]的转换率为: " + (sum.toDouble / value))
        }
    }
}
