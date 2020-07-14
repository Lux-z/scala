package com.atguigu.bigdata.scala02

/**
 * @author lux_zhang
 * @create 2020-05-19 18:46
 */
object NetUser {
    val userName = "zhangsan"
    val userAge = 18
    def ++() {
        if (userAge >= 18) "A Man!!!" else "A Child..."
    }
}
