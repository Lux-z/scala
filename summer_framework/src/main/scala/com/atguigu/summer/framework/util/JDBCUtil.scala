package com.atguigu.summer.framework.util

import java.sql.Connection
import java.util

import com.alibaba.druid.pool.DruidDataSourceFactory
import javax.sql.DataSource


/**
 * @author lux_zhang
 * @create 2020-06-15 18:15
 */
object JDBCUtil {
    //创建连接池对象
    var dataSource:DataSource = init()

    //连接池的初始化  使用Druid连接池对象
    def init():DataSource = {
        val paramMap = new util.HashMap[String,String]()
        paramMap.put("driverClassName", PropertiesUtil.getValue("jdbc.driver.name"))
        paramMap.put("url", PropertiesUtil.getValue("jdbc.url"))
        paramMap.put("username", PropertiesUtil.getValue("jdbc.user"))
        paramMap.put("password", PropertiesUtil.getValue("jdbc.password"))
        paramMap.put("maxActive", PropertiesUtil.getValue("jdbc.datasource.size"))

        DruidDataSourceFactory.createDataSource(paramMap)
    }

    //从连接池中获取连接对象
    def getConnection():Connection = {
        dataSource.getConnection
    }
}
