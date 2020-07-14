package com.atguigu.bigdata.scala08

/**
 * @author lux_zhang
 * @create 2020-05-27 21:52
 */
object ScalaCollectionMethod {
    def main(args: Array[String]): Unit = {
        //集合的常用方法
        val list = List(1,2,3,4)
        //获取集合长度:scala底层size实现的是length
        list.length
        list.size
        //遍历数据 foreach   mkString   iterator
        list.isEmpty//判断集合是否为空
        //集合元素运算
        list.sum
        list.max
        list.min
        list.product//元素乘积
        //获取集合的头 第一个元素
        list.head
        //获取集合的尾 //除了头都是尾
        list.tail
        //获取集合最有一个元素
        list.last
        //反转集合元素
        println(list.reverse)
        //判断数据是否存在 contains(判断的数据)
        list.contains(1)
        // 数据去重 转换为Set数据集 、distinct
        list.distinct
        //获取数据
        println(list.take(2))

    }

}
