package com.atguigu.bigdata.scala

import scala.io.Source

/**
 * @author lux_zhang
 * @create 2020-05-18 22:44
 */
object InPut {
    def main(args: Array[String]): Unit = {
        val line = Source.fromFile("output/test.txt").getLines()
        while (line.hasNext){
            println(line.next())
        }
    }
}
