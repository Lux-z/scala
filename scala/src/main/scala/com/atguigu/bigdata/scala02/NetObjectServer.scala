package com.atguigu.bigdata.scala02

import java.io.ObjectInputStream
import java.net.ServerSocket

/**
 * @author lux_zhang
 * @create 2020-05-19 18:45
 */
object NetObjectServer {
    def main(args: Array[String]): Unit = {
        //服务器建立通信  ServerSocket()
        val server = new ServerSocket(6666)
        //接收客户端请求
        while (true) {
            println("等待数据传输")
            //反序列化
            val client = server.accept()
            val input = new ObjectInputStream(client.getInputStream)

            val user = input.readObject()
            println(s"接收来自客户端的请求：$user")
        }
    }
}
