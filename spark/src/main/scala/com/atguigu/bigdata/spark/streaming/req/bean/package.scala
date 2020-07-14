package com.atguigu.bigdata.spark.streaming.req

/**
 * @author lux_zhang
 * @create 2020-06-19 8:37
 */
package object bean {

    case class Click_Log(ts:Long,area:String,city:String,userid:String,adid:String)

}
