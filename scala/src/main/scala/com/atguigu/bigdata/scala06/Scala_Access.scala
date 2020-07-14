package com.atguigu.bigdata.scala06

import scala.beans.BeanProperty

/**
 * @author lux_zhang
 * @create 2020-05-25 18:28
 */
object Scala_Access {
    def main(args: Array[String]): Unit = {
        /*
        scala中的四种访问权限
        private        : 私有权限    同类
        private[包名]  : 包私有权限，同类，同包
        protected      : 受保护权限，同类，子类
        (default)      : 公共权限，  任意的地方, scala中没有public关键字
                        scala的源码文件中可以声明多个公共类
         */

        //private:
        //如果类中的属性声明为private，那么编译器生成的get()、set()同样会用private修饰，所以无法调用
        val user = new User()
//        user.name
        //scala如何与java保持同步，提供get、set方法：通过注解@BeanProperty
        user.getName
        user.age
        //protected:同类，子类可以使用,但是子类不在同一个包下也不可以使用
        //user.sex
    }

    class User {
        //使用@BeanProperty注解后，属性不能声明为private
        @BeanProperty
        var name = "zhangsan"
        //private[包名]
        private[scala06] var age = 16
        protected val sex = "男"
    }
    class SubUser extends User{
        def testSub = {
            println(this.sex)
        }
    }

}
