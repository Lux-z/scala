package com.atguigu.bigdata.scala

import scala.io.StdIn

/**
 * @author lux_zhang
 * @create 2020-05-18 10:19
 */
object HelloScala {
    def main(args: Array[String]): Unit = {
        println("Hello Scala")
        var name: String = "hello"
        println(name)
        println(HelloScala.life())
        val age = StdIn.readLine()
    }

    def life(): Long = {
        return Long.MaxValue / 1000 / 3600 / 24 / 365
    }
}
