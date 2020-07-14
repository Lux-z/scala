package com.atguigu.bigdata.scala03

/**
 * @author lux_zhang
 * @create 2020-05-20 19:19
 */
object StarStarTower {
    def main(args: Array[String]): Unit = {
        starTower(9)
    }

    def starTower(line: Int) = {
        for (i <- 1 to 2 * line by 2) {
            println(" " * {line - i / 2} + "*" * i)
        }
    }
}
