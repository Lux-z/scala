package com.atguigu.bigdata.scala03

/**
 * @author lux_zhang
 * @create 2020-05-20 21:06
 */
object ScalaFunction {
    /*
    函数式编程特点：
    函数和方法的区别：方法也是函数，定义在类中，作为类的一部分的函数称之为方法，其他地方封装的功能称之为函数
     */
    def main(args: Array[String]): Unit = {
        //函数声明方式：def 函数名(参数列表)[：返回值类型] = {函数体}
        def fun(a: Int) = {
            println(s"age = $a")
        }

        fun(18)

        println("============================================================")

        //无参数无返回值类型函数：参数列表可以省略，返回值可以省略(参数列表小括号省略则调用时不能加小括号)函数体只有一行时，等号也可以省略
        def fun1 {
            println("至少我还有名字")
        }

        fun1
        println("============================================================")

        //多参数有返回值类型函数
        //函数参数个数最大为22个。声明时参数可以超过22个，但是将函数作为对象赋值给变量时会报错
        def fun2(name: String, age: String): String = {
            return s"""
                      |{username:$name,age:$age}
                    """.stripMargin
        }

        println(fun2("zhangsan", "18"))
        println("============================================================")

        //可变形参：相同类型的参数多次出现，但是不确定个数
        //可变参数必须声明在参数列表的最后
        def fun3(i: Int*): Unit = {
            println(i)
        }

        fun3(10)
        fun3(1, 2, 3)
        fun3(1, 2, 3, 4, 5, 6, 7)

        def fun4(s: String, i: Int*): Unit = {
            println(
                s"""
                   |{name:$s,socre:$i}
                   |""".stripMargin)
        }

        fun4("zhangsan", 60, 60, 60, 60, 60)

        println("============================================================")

        //参数默认值:在声明参数时进行初始化
        //scala中函数的参数是使用val声明的，无法修改
        def fun5(name : String,password : String = "000000"): Unit ={
            println(
                s"""
                  |{name:$name,password:$password}
                 """.stripMargin)
        }
        //如果函数存在有默认值的参数，调用时可以不传此参数
        fun5("zhangsan")
        //调用函数时传递了有默认值的参数，会覆盖参数的默认值
        fun5("lisi","123456")

        println("============================================================")

        //如果有多个参数，可以通过添加参数名指定参数
        def fun6(name : String,password : String = "000000",tel : String): Unit ={
            println(
                s"""
                   |{name:$name,password:$password,tel:$tel}
                 """.stripMargin)
        }
        fun6(name = "wangwu",tel = "12345678901")
    }
}
