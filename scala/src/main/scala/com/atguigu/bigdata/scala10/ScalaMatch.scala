package com.atguigu.bigdata.scala10

import scala.collection.mutable

/**
 * @author lux_zhang
 * @create 2020-06-01 19:15
 */
object ScalaMatch {
    def main(args: Array[String]): Unit = {
//        //模式匹配 匹配常量
//        var a : Int = 10
//        var b : Int = 20
//        var operator : Char = '*'
//        val result = operator match {
//            case '+' => a + b
//            case '-' => a - b
//            case '*' => a * b
//            case '/' => a / b
//        }
//        println(result)
//
//        //匹配类型
//        //匹配类型不考虑泛型   数组的泛型是类型的一部分
//        //下划线能够用来代替参数，如果需要使用这个参数则需要显示的声明
//        def describe(x: Any) = x match {
//            case i: Int => "Int"
//            case s: String => "String hello"
//            case m: List[_] => "List"
//            case c: Array[Int] => "Array[Int]"
//            case someThing => "something else " + someThing
//        }
//
//        println(describe(Array("a","b")))
//
//        //匹配数组
//        val sorted = Array("one","two","three").max
//        println(sorted)
//        for(i<- 1 to 3 if i != 1)
//            println(i)

        val strings: Array[String] = "one,two,three,four,two,two,three,four,".split(",")
        val map = new mutable.HashMap[String,Int]
        for(key<-strings){
            map(key) = map.getOrElse(key, 0) + 1
        }
        val i: Int = map("two")
        println(i)
    }

}
