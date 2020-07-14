package com.atguigu.summer.framework.util

import java.util.ResourceBundle

/**
 * @author lux_zhang
 * @create 2020-05-24 11:10
 */
object PropertiesUtil {
    //绑定配置文件
    //ResourceBundle专门用于读取配置文件properties，所以读取时不需要添加扩展名
    val summer: ResourceBundle = ResourceBundle.getBundle("summer")
    def getValue(ket : String) = {
        summer.getString(ket)
    }

}
