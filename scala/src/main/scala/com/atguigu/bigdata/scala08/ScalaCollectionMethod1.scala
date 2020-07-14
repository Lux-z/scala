package com.atguigu.bigdata.scala08

/**
 * @author lux_zhang
 * @create 2020-05-27 21:52
 */
object ScalaCollectionMethod1 {
    def main(args: Array[String]): Unit = {
        val list = List(1,2,3,4)
        //map:映射转换 自定义转换规则，将原集合转换为一个新的集合
        println(list.map(_ * 2).mkString(","))

        //flatten 扁平化  将整体拆分成个体使用
        val list1 = List(list,List(5,6,7))
        val flatten: List[Int] = list1.flatten
        println(flatten)
        //扁平化操作默认操作外层数据，通过多次调用操作多层集合
        val list2 = List(list1,list1)
        println(list2)
        println(list2.flatten)
        println(list2.flatten.flatten)

        //flatMap 扁平映射
        list2.flatten.flatMap(_.map(_*2))

        //filter 按照指定的规则过滤数据
        println(list.filter(_ % 2 == 0))
        val list3 = List("hello",2,"spark",3,"scala")
        println(list3.filter(_.isInstanceOf[String]))

        //groupBy 分组，按照指定的规则聚合集合的元素
        println(List("hello", "hello", "spark").groupBy(s => s))

        //sortBy 将集合中每一个数据按照指定的规则进行排序,默认排序为升序
        val list4 = List(3,11,4,2,1)
        println(list.sortBy(num => num))
        println(list.sortBy(num => -num))//降序 只能数值型数据通过-实现降序
        println(list.sortBy(num => num).reverse)//反转 原理上并没有进行降序排序
        println(list.sortBy(num => num)(Ordering.Int.reverse)) //通过函数柯里化实现降序
    }

}
