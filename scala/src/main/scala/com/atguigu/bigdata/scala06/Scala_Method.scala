package com.atguigu.bigdata.scala06

/**
 * @author lux_zhang
 * @create 2020-05-25 19:12
 */
object Scala_Method {
    def main(args: Array[String]): Unit = {
        //apply:用来构件对象,一般用于伴生对象中构件对象
        //伴生对象可以访问伴生类中私有的属性和方法
        //scala可以自动识别apply方法，调用伴生对象的apply方法时可以省略apply方法名
        //apply中可以构件其他类的对象
//        val user = User.apply()
        //如果伴生对象不使用小括号，等同于将伴生对象赋值给变量，而不是调用伴生对象的apply方法
        //apply方法如果要被编译器识别，不能省略小括号
        val user = User()
        println(user)
        //apply方法可以重载
    }

}

class User {

}
object User {
    def apply(): User = new User

    def apply(name : String): User = {
        new User()
    }
}
