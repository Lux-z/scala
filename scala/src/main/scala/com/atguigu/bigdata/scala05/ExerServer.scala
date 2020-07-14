package com.atguigu.bigdata.scala05

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

import com.atguigu.summer.framework.bean.Task
import com.atguigu.summer.framework.core.Application

/**
 * @author lux_zhang
 * @create 2020-05-24 10:57
 */
object ExerServer extends Application {
    def main(args: Array[String]): Unit = {
        start("serverSocket") {
            val server = envData.asInstanceOf[ServerSocket]
            while (true) {
                println("等待客户端请求")
                //接收客户端请求
                var client = server.accept()
                //启动线程处理客户端请求
                new Thread(
                    new Runnable {
                        override def run(): Unit = {
                            val in = new ObjectInputStream(client.getInputStream)
                            val task = in.readObject().asInstanceOf[Task]
                            client.shutdownInput()

                            val out = new ObjectOutputStream(client.getOutputStream)
                            val result = task.compute()
                            out.writeObject(result)
                            println("成功返回数据")
                            client.shutdownOutput()

                            //关闭获取客户端的流
                            if (!client.isClosed) client.close()
                            //释放对象,直接回收client
                            client = null
                        }
                    }
                ).start()

            }
        }
    }

}
