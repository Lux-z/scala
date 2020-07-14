package com.atguigu.summer.framework.core

import java.net.{ServerSocket, Socket}

import com.atguigu.summer.framework.util.{EnvUtil, PropertiesUtil}
import org.apache.spark.streaming.StreamingContext


/**
 * @author lux_zhang
 * @create 2020-05-24 10:37
 */
trait TApplication {
    var envData: Any = null

    /**
     * 启动应用
     *
     * @s 服务类型 默认为JDBC
     */
    def start(s: String = "JDBC")(op: => Unit): Unit = {
        //1.初始化环境
        if (s == "socket") {
            envData = new Socket(
                PropertiesUtil.getValue("server.host"),
                PropertiesUtil.getValue("server.port").toInt)
        } else if (s == "serverSocket") {
            envData = new ServerSocket(PropertiesUtil.getValue("server.port").toInt)
        } else if (s == "spark") {
            EnvUtil.getEnv()
        } else if (s == "sparkStreaming" ) {
            envData = EnvUtil.getStreamingEnv()
        }

        //2.逻辑代码
        try {
            op
        } catch {
            case ex: Exception => println(s"业务执行失败：${ex.getMessage}")
        }
        //3.环境的关闭
        if (s == "socket") {
            val socket = envData.asInstanceOf[Socket]
            if (!socket.isClosed) socket.close()
        } else if (s == "serverSocket") {s
            val server = envData.asInstanceOf[ServerSocket]
            if (!server.isClosed) server.close()
        } else if (s == "spark") {
            EnvUtil.clear()
        } else if (s == "sparkStreaming" ) {
            val ssc: StreamingContext = envData.asInstanceOf[StreamingContext]
            ssc.start()
            ssc.awaitTermination()
        }
    }
}
