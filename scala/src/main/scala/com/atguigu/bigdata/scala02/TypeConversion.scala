package com.atguigu.bigdata.scala02

/**
 * @author lux_zhang
 * @create 2020-05-19 20:14
 */

object TypeConversion {
    def main(args: Array[String]): Unit = {
        //自动类型转换
        val b : Byte = 10
        val s : Short = 2
        /*
        编译报错，然而可以运行，常量计算过程在编译时就可以完成
        变量：指向内存中开辟的一块空间，在程序运行后才能知道变量存储的值
        常量：值，各种数据类型的对象，在编译期间就可以进行运算
        */
        val c : Char = 'A' + 1

        val i : Int = c + s
        val L : Long = i

        //强制类型转换
        //基本上scala的数据类型都提供了相互转换的方法，需要通过调用方法来强制转换类型
        //强制类型转换会有精度损失
        val value = "123"
        val int = value.toInt
        println(int)
        val int2 = 128
        val byte = int2.toByte// 127 + 1 = -128
        println(byte)
    }
}
