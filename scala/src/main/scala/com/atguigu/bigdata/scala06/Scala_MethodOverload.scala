package com.atguigu.bigdata.scala06

/**
 * @author lux_zhang
 * @create 2020-05-25 20:08
 */
object Scala_MethodOverload {
    /*
    多个方法名称相同，但是参数列表( 参数个数，参数类型，参数顺序 )不相同
    数值类型，在重载的方法中会提升精度。
    引用类型，在重载的方法如果找不到对应的类型。会从类树往上查找
     */
    def main(args: Array[String]): Unit = {
        //方法的重载在AnyVal数据类型中会存在精度的概念，当调用重载的方法时，传入的参数没有对应类型的
        //      重载方法，则会提升数据精度调用精度变化最小的参数的重载方法
        val b:Byte = 10
        test(b)//输出bbbb

    }

//    def test(b:Byte) = {
//        println("aaaa")
//    }
    def test(s:Short) = {
        println("bbbb")
    }
    def test(c:Char) = {
        println("cccc")
    }
    def test(i:Int) = {
        println("dddd")
    }

}
