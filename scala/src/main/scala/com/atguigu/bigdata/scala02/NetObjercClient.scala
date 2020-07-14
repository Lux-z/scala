package com.atguigu.bigdata.scala02

import java.io.ObjectOutputStream
import java.net.Socket

/**
 * @author lux_zhang
 * @create 2020-05-19 18:45
 */
object NetObjercClient {
    def main(args: Array[String]): Unit = {
        //与服务器开放端口建立连接
        val socket = new Socket("localhost",6666)
        //输出序列化对象
        val name = NetUser.++()
        val out = new ObjectOutputStream(socket.getOutputStream)
        out.writeObject(name)
        println(s"发送对象数据:$name")
        socket.close()
    }
}
