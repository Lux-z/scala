package com.atguigu.bigdata.scala

import java.io.{File, FileWriter, PrintWriter}

/**
 * @author lux_zhang
 * @create 2020-05-18 22:39
 */
object OutPut {
    def main(args: Array[String]): Unit = {
        val writer = new PrintWriter(new File("output/test.txt"))
        writer.write("hello \nworld")
        writer.close()
    }
}
