package com.atguigu.bigdata.scala07

/**
 * @author lux_zhang
 * @create 2020-05-26 10:20
 */
object ScalaMethodAbstract {
    def main(args: Array[String]): Unit = {

        //抽象类无法直接构造对象，需要重写抽象类中的所有抽象属性和方法
        new UserAbstract {
            override var name: String = _

            override def test(): Unit = ???
        }
        //使用子类继承抽象类，构造子类的同时构造抽象类
        new subUserAbstract
        new subUserAbstract1
    }
}
abstract class UserAbstract {
    //如果一个类中有抽象方法或抽象属性，那么这个类一定是抽象类，使用abstract修饰
    //抽象类中不一定要有抽象方法
    //抽象属性：所谓抽象属性，其实就是不完整的属性：属性只有声明而没有初始化
    var name:String
    //抽象方法:无需使用abstract修饰
    def test():Unit
}
class subUserAbstract extends UserAbstract {
    //子类继承抽象类，需要将抽象类中不完整的内容补全，否则子类也是抽象类
    override var name: String = _

    override def test(): Unit = {

    }
}

abstract class UserAbstract1 {
    def test() = {

    }
}

class subUserAbstract1 extends UserAbstract1 {
    //如果抽象类中没有不完整的抽象方法，子类不用声明为抽象类，可以直接构件对象
    //如果子类重写父类中完整的方法，直接补充完整或显示的增加override关键字修饰
//    def test() = { }
    override def test() = {}
}