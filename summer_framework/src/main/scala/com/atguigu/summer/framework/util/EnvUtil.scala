package com.atguigu.summer.framework.util

import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author lux_zhang
 * @create 2020-06-09 10:12
 */
object EnvUtil {
    private val scLocal = new ThreadLocal[SparkContext]
    private val sscLocal = new ThreadLocal[StreamingContext]

    def getStreamingEnv( time : Duration = Seconds(5) ) = {
        var ssc: StreamingContext = sscLocal.get()
        if ( ssc == null ) {
            // 如果获取不到环境对象
            val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkApplication")
            // 创建新的环境对象
            ssc = new StreamingContext(sparkConf, time)
            // 保存到共享内存中
            sscLocal.set(ssc)
        }

        ssc
    }

    def getEnv():SparkContext= {
        val sc: SparkContext = scLocal.get()
        if (sc == null) {
            val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkApplication")

            val sc = new SparkContext(conf)

            scLocal.set(sc)
        }
        sc
    }

    def clear():Unit = {
        getEnv().stop()
        scLocal.remove()
    }
}
