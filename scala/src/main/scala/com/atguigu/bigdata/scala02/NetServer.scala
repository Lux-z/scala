package com.atguigu.bigdata.scala02

import java.net.ServerSocket

/**
 * 网络传输IO操作
 *
 * @author lux_zhang
 * @create 2020-05-19 8:56
 */
object NetServer {
    def main(args: Array[String]): Unit = {
        //服务器建立通信  ServerSocket()
        val server = new ServerSocket(6666)
        //接收客户端请求
        while (true) {
            println("等待数据传输")
            val client = server.accept()
            val data = client.getInputStream.read()
            println(s"接收来自客户端的请求：$data")
        }
    }
}
