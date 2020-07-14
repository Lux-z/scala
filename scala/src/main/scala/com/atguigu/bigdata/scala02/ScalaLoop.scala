package com.atguigu.bigdata.scala02

/**
 * @author lux_zhang
 * @create 2020-05-19 21:40
 */
object ScalaLoop {
    def main(args: Array[String]): Unit = {
        //scala中的循环结构 for(循环变量 <- 数据集){循环体}数据集表示方式可以为Range(a,b),a to b,a until b
        //可以通过指定步距step控制循环
        for (i <- Range(1,5)){//Rangr(a,b)的范围为[a,b）
            print(i + "\t")
            println()
        }

        for (i <- 1 to 5){//a to b的范围为[a,b]
            print(i + "\t")
            println()
        }

        for (i <- 1 until 5){//a until b的范围为[a,b）
            print(i + "\t")
            println()
        }

        for (i <- Range(1,5,2)){
            print(i + "\t")
            println()
        }

        for (i <- 1 to 5 by 2){
            print(i + "\t")
            println()
        }

        for (i <- 1 until 5 by 2){
            print(i + "\t")
            println()
        }
    }
}
