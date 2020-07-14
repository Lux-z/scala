package com.atguigu.bigdata.scala02

/**
 * @author lux_zhang
 * @create 2020-05-19 20:52
 */
object Operator {
    /*
      scala在类中定义运算方法，实际使用的运算符是运算数据类型的类中的方法
    */
    def main(args: Array[String]): Unit = {
        //Scala中 字符串比较
        //scala中 == 和 equal作用相同，都是比较字符串内容是否相同 定义一个eq方法用于比较字符串存储的内存地址

        //==与equal有一定的区别，在源码编译时，==会处理字符串为空值的情况，而equal不会处理，所以euqals左边的对象不能为空
        val manager = new String ("zhangsan")
        val employee = new String("zhangsan")
        println(manager == employee)
        println(manager.equals(employee))
        println(manager.eq(employee))

        //逻辑运算符 & 二进制数各位数取&
        println(2 & 3) // 0000 0010 & 0000 0011 = 0000 0010
        /*
        HashMap集合中通过Hash值与底层table数组&运算得出数据存储位置hash(key.hashCode & (length - 1))
        要求数组扩容必须为2倍,保证(length - 1)的二进制数尾数全部为最大值，避免数据倾斜

         */
    }
}
