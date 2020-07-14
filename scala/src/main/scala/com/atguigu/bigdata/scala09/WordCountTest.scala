package com.atguigu.bigdata.scala09

/**
 * @author lux_zhang
 * @create 2020-05-29 20:54
 */
object WordCountTest {
    def main(args: Array[String]): Unit = {
        //不同省份商品点击排行
        val list = List(
            ("zhangsan", "河北", "鞋"),
            ("lisi", "河北", "衣服"),
            ("wangwu", "河北", "鞋"),
            ("zhangsan", "河南", "鞋"),
            ("lisi", "河南", "衣服"),
            ("wangwu", "河南", "鞋"),
            ("zhangsan", "河南", "鞋"),
            ("lisi", "河北", "衣服"),
            ("wangwu", "河北", "鞋"),
            ("zhangsan", "河北", "鞋"),
            ("lisi", "河北", "衣服"),
            ("wangwu", "河北", "帽子"),
            ("zhangsan", "河南", "鞋"),
            ("lisi", "河南", "衣服"),
            ("wangwu", "河南", "帽子"),
            ("zhangsan", "河南", "鞋"),
            ("lisi", "河北", "衣服"),
            ("wangwu", "河北", "帽子"),
            ("lisi", "河北", "衣服"),
            ("wangwu", "河北", "电脑"),
            ("zhangsan", "河南", "鞋"),
            ("lisi", "河南", "衣服"),
            ("wangwu", "河南", "电脑"),
            ("zhangsan", "河南", "电脑"),
            ("lisi", "河北", "衣服"),
            ("wangwu", "河北", "帽子")
        )

        //1.先进行数据清洗，去除冗余数据
        val data: List[String] = list.map(kv=>kv._2 + " " + kv._3)
        //2.按照内容聚合数据,转换为List后再转换数据结构，将省份作为k，商品的集合作为v
        val StringToList: Map[String, List[(String, Int)]] =
            data.groupBy(word => word).mapValues(_.size).toList.map(kv => {
                val ks: Array[String] = kv._1.split(" ")
                (ks(0), (ks(1), kv._2))
            }).groupBy(_._1).mapValues(_.map(_._2))
        //3.v集合内排序
        val result: Map[String, List[(String, Int)]] = StringToList.mapValues(_.sortWith(_._2 > _._2))
        println(result)

//        println(list.map(t => t._2 + " " + t._3).groupBy(word => word).mapValues(_.size).toList.map(kv => {
//            val ks = kv._1.split(" ")
//            (ks(0), (ks(1), kv._2))
//        }).groupBy(_._1).mapValues(_.map(_._2).sortWith(_._2 > _._2)))

    }
}
