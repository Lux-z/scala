package com.atguigu.bigdata.spark.exer.core.req.service

import com.atguigu.bigdata.spark.core.req.bean
import com.atguigu.bigdata.spark.exer.core.req.dao.PageChangeDao
import com.atguigu.summer.framework.core.TService
import org.apache.spark.rdd.RDD

/**
 * 页面单跳转换率统计
 * @author lux_zhang
 * @create 2020-06-20 10:10
 */
class PageChangeService extends TService{
    private val pageChangeDao = new PageChangeDao

    override def analysis() = {
        //对指定的页面流程进行页面单跳转换率的统计
        val needs = List(1,2,3,4,5,6,7)
        val pageflow: List[String] = needs.zip(needs.tail).map(page=>page._1 + "-" + page._2)

        val logsRDD: RDD[bean.UserVisitAction] = pageChangeDao.getUserVisitAction("input11/user_visit_action.txt")
        logsRDD.cache()
        //过滤数据 聚合页面id的访问总数
        val reduceRDD: Array[(Long, Int)] = logsRDD.filter(log=>needs.init.contains(log.page_id.toInt)).map(action=>(action.page_id,1)).reduceByKey(_+_).collect()

        //计算分子
        //按照sessionid分组
        val groupRDD: RDD[(String, Iterable[bean.UserVisitAction])] = logsRDD.groupBy(_.session_id)

        val pageflowRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(iter => {
            //按照访问时间排序
            val actions: List[bean.UserVisitAction] = iter.toList.sortWith((left, right) => left.action_time < right.action_time)
            //对数据结构进行转换(page-page,1)
            val pageList: List[Long] = actions.map(_.page_id)
            val pageToPage: List[(String, Int)] = pageList.zip(pageList.tail).map(page => (page._1 + "-" + page._2, 1))
            //过滤需要统计的页面跳转
            pageToPage.filter(tup => pageflow.contains(tup._1))
        })

        //对数据结构再转换，聚合
        val sumRDD: RDD[(String, Int)] = pageflowRDD.map(_._2).flatMap(data=>data).reduceByKey(_+_)

        //
        sumRDD.foreach{
            case (pageflow,sum)=>{
                val pageId: Long = pageflow.split("-")(0).toLong
                val value: Int = reduceRDD.toMap.getOrElse(pageId,1)
                println("页面跳转["+pageId+"]的转换率为: " + (sum.toDouble / value))
            }
        }
    }
}
