package com.atguigu.bigdata.scala06

/**
 * @author lux_zhang
 * @create 2020-05-25 20:54
 */
object Scala_MethodInstance {
    def main(args: Array[String]): Unit = {
        //scala中提供了2种不同类型的构造方法
        //1.主构造方法：在类名后的构造方法，可以完成类的初始化
        //2.辅助构造方法：为了完成类初始化的辅助功能而提供的构造方法：声明方式为：def this() = {}
        //2.1在使用辅助构造方法时，必须直接或间接地调用主构造方法，辅助构造方法也存在重载的概念
        //2.2辅助构造方法间接调用主构造函数时，需要保证调用的其他辅助构造函数已经声明过。

        //构造方法执行时会完成类的主体内容的初始化
        val user = new User()
        //辅助构造方法:必须直接或间接地调用主构造方法
        val zhangsan = new User("zhangsan")
        println(zhangsan.name)
        val lisi = new User("lisi",18)

        //可以使用关键字var，val将构造参数当成类的属性使用
        val emp = new Emp("lisi")
        println(emp.name)

        //构造方法的执行顺序：先主后辅，由父及子
    }

    class User() {
        var name:String = _
        var age:Int = _
        println("******")
        def User()= {
            println("初始化...")
        }

        def this(name:String) = {
            this()
            this.name = name
        }

        def this(name:String,age:Int) = {
            this(name)
            this.age = age
        }
    }

    class Emp(var name:String) {

    }
}
