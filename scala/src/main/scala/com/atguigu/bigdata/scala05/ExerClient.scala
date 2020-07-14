package com.atguigu.bigdata.scala05

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.atguigu.summer.framework.bean.Task
import com.atguigu.summer.framework.core.Application

/**
 * @author lux_zhang
 * @create 2020-05-24 10:58
 */
object ExerClient extends Application {
    def main(args: Array[String]): Unit = {
        start("socket") {
            val client = envData.asInstanceOf[Socket]
            //发送请求
            val out = new ObjectOutputStream(client.getOutputStream)
            //序列化
            val task = new Task()
            task.data = 10
            task.logic = _ * 2
            out.writeObject(task)
            println("请求发送成功")
            out.flush()
            //单独关闭输出流
            client.shutdownOutput()

            //接收返回的结果
            val in = new ObjectInputStream(client.getInputStream)
            val result = in.readObject().asInstanceOf[Int]
            println(s"计算结果为：$result")
            client.shutdownInput()
        }
    }
}
