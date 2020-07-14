package com.atguigu.summer.framework.bean

/**
 * @author lux_zhang
 * @create 2020-05-24 15:08
 */
class Task extends Serializable {
    var data :Int = 2
    var logic : Int => Int = null
    def compute() = logic(data)
}
