package com.atguigu.bigdata.scala04

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

/**
 * 如果一台机器想让另外一台机器执行函数功能怎么办？
 *
 * @author lux_zhang
 * @create 2020-05-22 19:05
 */
object ExerCluster01 {
    //Socket + IO + 序列化
    def main(args: Array[String]): Unit = {
        //1.建立通信接口
        val socket = new ServerSocket(9999)
        //2.接收并反序列化函数
        println("等待数据传输")
        val cluster02 = socket.accept()
        val input = new ObjectInputStream(cluster02.getInputStream)readObject()
        val value = input.asInstanceOf[Int=>Int]
        //3.处理函数
        val result = PorcessFun(6,value)
        //4.将函数的值返回
        val socket1 = new Socket("localhost",6666)
        val ret = new ObjectOutputStream(socket1.getOutputStream)
        ret.writeObject(result)
        socket1.close()


    }

    def PorcessFun(i:Int,f:Int=>Int) = {
        f(i)
    }

}
