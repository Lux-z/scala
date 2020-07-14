package com.atguigu.bigdata.scala02

import java.net.Socket

/**
 * @author lux_zhang
 * @create 2020-05-19 18:22
 */
object NetClient {
    def main(args: Array[String]): Unit = {
        //与服务器开放端口建立连接
        val socket = new Socket("localhost",6666)
        //输出
        socket.getOutputStream.write(127)
        println("发送数据")
        socket.close()
        def main(args: Array[String]): Unit = {
            scala.util.control.Breaks.breakable {
                for ( i <- 1 to 5 ) {
                    if ( i == 3 ) {
                        scala.util.control.Breaks.break
                    }
                    println(i)
                }
            }
        }
    }
}
