package com.atguigu.bigdata.spark.exer.core.req.service

import com.atguigu.bigdata.spark.core.req.bean
import com.atguigu.bigdata.spark.exer.core.req.bean.HotCategory
import com.atguigu.bigdata.spark.exer.core.req.dao.Top10SessionDao
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.EnvUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

/**
 * @author lux_zhang
 * @create 2020-06-19 22:06
 */
class Top10SessionTService extends TService{

    private val top10SessionDao = new Top10SessionDao

    override def analysis(data: Any) = {
        val logsRDD: RDD[bean.UserVisitAction] = top10SessionDao.getUserVisitAction("input11/user_visit_action.txt")

        //获取top10数据
        val top10: List[HotCategory] = data.asInstanceOf[List[HotCategory]]
        val cidList: List[String] = top10.map(_.categoryId)

        //使用广播变量传递数据
        val cidBC: Broadcast[List[String]] = EnvUtil.getEnv().broadcast(cidList)

        //过滤数据
        val filterRDD: RDD[bean.UserVisitAction] = logsRDD.filter(cid => {
            if (cid.click_category_id != "-1") {
                cidBC.value.contains(cid.click_category_id)
            } else false
        })

        //将过滤后的数据进行处理 (品类_session，1)
        val reduceRDD: RDD[(String, Int)] = filterRDD.map(log => {
            ((log.click_category_id + "_" + log.session_id), 1)
        }).reduceByKey(_ + _)

        //结构转换(品类,(session,sum))
        val mapRDD: RDD[(String, (String, Int))] = reduceRDD.map {
            case (tuple, sum) => {
                val strings: Array[String] = tuple.split("_")
                (strings(0), (strings(1), sum))
            }
        }
        //按照品类分组，value排序
        mapRDD.groupByKey().mapValues(_.toList.sortWith((left,right)=>left._2 > right._2).take(10)).collect()
    }
}
