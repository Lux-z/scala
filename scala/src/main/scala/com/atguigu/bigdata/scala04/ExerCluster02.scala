package com.atguigu.bigdata.scala04

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

/**
 * @author lux_zhang
 * @create 2020-05-22 19:07
 */
object ExerCluster02 {
    def main(args: Array[String]): Unit = {
        //1.建立通信
        val socket = new Socket("localhost",9999)
        //2.序列化函数
        val SimpleFun = fun _
        val out = new ObjectOutputStream(socket.getOutputStream)
        out.writeObject(SimpleFun)
        println("发送计算请求")
        socket.close()
        //3.接收函数的执行结果
        val socket1 = new ServerSocket(6666)
        val resultNum = socket1.accept()
        val result = new ObjectInputStream(resultNum.getInputStream).readObject()
        println(s"函数执行结果为：$result")
    }

    //工具人
    def fun(i:Int) = i * 2
}
