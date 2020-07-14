package com.atguigu.bigdata.spark.core.req.service

import com.atguigu.bigdata.spark.core.req.bean
import com.atguigu.bigdata.spark.core.req.bean.HotCategory
import com.atguigu.bigdata.spark.core.req.dao.HotCategorySessionTop10Dao
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.EnvUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD


/**
 * 在需求一的基础上，增加每个品类用户session的点击统计
 * @author lux_zhang
 * @create 2020-06-09 14:04
 */
class HotCategorySessionTop10Service extends TService{
    val hotCategoryTop10Dao = new HotCategorySessionTop10Dao

    override def analysis(data: Any) = {
        val top10: List[HotCategory] = data.asInstanceOf[List[HotCategory]]

        val listCid: List[String] = top10.map(_.categoryId)
        //使用广播变量实现数据的传播
        val bc: Broadcast[List[String]] = EnvUtil.getEnv().broadcast(listCid)

        //获取用户行为的数据
        val actionAdd: RDD[bean.UserVisitAction] = hotCategoryTop10Dao.getUserVisitAction("input11/user_visit_action.txt")

        //过滤
        val filterData: RDD[bean.UserVisitAction] = actionAdd.filter(action => {
            if (action.click_category_id != -1) {
                bc.value.contains(action.click_category_id.toString)
            } else false
        })

        //处理过滤后的数据 (品类_session,sum)
        val reduceRdd: RDD[(String, Int)] = filterData.map(uva => {
            (uva.click_category_id + " " + uva.session_id, 1)
        }).reduceByKey(_+_)

        //将统计后的结构进行统计后的转换 (品类，(session,sum))
        val mapRdd: RDD[(String, (String, Int))] = reduceRdd.map(map => {
            val strings: Array[String] = map._1.split(" ")
            (strings(0), (strings(1), map._2))
        })

        //分组 排序
        mapRdd.groupByKey().mapValues(_.toList.sortWith((left,right)=> left._2 > right._2).take(10)).collect()

    }
}
