package com.atguigu.bigdata.scala03

/**
 * @author lux_zhang
 * @create 2020-05-21 15:26
 */
object ScalaHomeWorkExer {
    def main(args: Array[String]): Unit = {
        //1. 如果想把一个任意的数字A通过转换后得到它的2倍，那么这个转换的函数应该如何声明和使用
        def doubleNum(i : Int)= 2 * i
        println(doubleNum(6))

        //2. 如果上一题想将数字A转换为任意数据B（不局限为数字），转换规则自己定义，该如何声明
        def exchange(i:Int): Any = if (i % 2 == 0) return i / 2 else return "no balance"
        println(exchange(6))

        //3. 如果函数调用：  test(10,20,c)的计算结果由参数c来决定，这个参数c你会如何声明
        def test(a:Int,b:Int,c:(Int,Int)=>Any) = c(a,b)
        println(test(10, 20, _ * _))

        //4. 如果设计一个用于过滤的函数，你会如何做？
        //要求：对传递的包含多个单词的字符串参数,根据指定的规则对word进行过滤
        //例子："hello world scala spark" => 首写字母为s => "scala, spark"
        def ETL(data:String,c:Char) {
            val strings = data.split(" ")
            for(word <- strings){
                if (word.charAt(0) == c)
                    print(s"$word\t")
//                else
//                    print("*\t")
            }
        }
        ETL("hello world scala spark", 's')


        def f4(str:String,f:String=>String):String={
            val result = new StringBuffer()
            val strs: Array[String] = str.split(" ")
            for (s <- strs) {
                result.append(f(s))
            }
            result.deleteCharAt(result.length - 1).toString
        }

        def filter1(str:String):String=if(str.startsWith("s")) str + "," else ""

        println(f4("hello world scala spark", filter1 _))

    }
}
