package com.atguigu.bigdata.scala08

import scala.collection.mutable.ArrayBuffer

/**
 * @author lux_zhang
 * @create 2020-05-27 9:42
 */
object ScalaArray {
    def main(args: Array[String]): Unit = {
        //循环遍历
        //foreach()  mkString()

        val ints: Array[Int] = Array(1,2,3)
        val ints1 = ints :+ 4
        ints.foreach(println(_))
        println(ints1.mkString(","))

        val buffer: ArrayBuffer[Int] = new ArrayBuffer[Int]
        buffer.append(1,2,3)
        println(buffer)
        println(buffer.mkString(","))
        buffer.update(0,2)
        println(buffer)

        //多维数组
        var myMatrix = Array.ofDim[Int](3,3)
        myMatrix.foreach(list=>list.foreach(println))

        //合并数组  concat()
        var array = Array(1,2,3)
        var array1 = Array(4,5,6)
        val concatArray: Array[Int] = Array.concat(array,array1)
        println(concatArray.mkString(","))

        // 创建并填充指定数量的数组  Array.fill
        val arr:Array[Int] = Array.fill[Int](5)(-1)
        arr.foreach(println)
    }
}
