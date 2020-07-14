package com.atguigu.bigdata.spark.core.req.helper

import com.atguigu.bigdata.spark.core.req.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-06-09 19:12
 */
class Top10Accumulator extends AccumulatorV2[(String,String),mutable.Map[String,HotCategory]]{
    private var categoryMap: mutable.Map[String, HotCategory] = mutable.Map[String,HotCategory]()

    override def isZero: Boolean = categoryMap.isEmpty

    override def copy(): AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] = new Top10Accumulator

    override def reset(): Unit = categoryMap.clear()

    override def add(v: (String, String)): Unit = {
        val category: String = v._1
        val action: String = v._2
        val hotCategory: HotCategory = categoryMap.getOrElse(category,HotCategory(category,0,0,0))
        action match {
            case "click" => hotCategory.clickCount += 1
            case "order" => hotCategory.orderCount += 1
            case "pay" => hotCategory.payCount += 1
            case _ =>
        }
        categoryMap(category) = hotCategory
    }

    override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, HotCategory]]): Unit = {
        other.value.foreach{
            case (category,hotCategory) => {
                val hc: HotCategory = categoryMap.getOrElse(category,HotCategory(category,0,0,0))
                hc.clickCount += hotCategory.clickCount
                hc.orderCount += hotCategory.orderCount
                hc.payCount += hotCategory.payCount

                categoryMap(category) = hc
            }
        }
    }

    override def value: mutable.Map[String, HotCategory] = categoryMap
}
