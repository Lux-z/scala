package com.atguigu.bigdata.scala05


/**
 * @author lux_zhang
 * @create 2020-05-24 15:49
 */
object ScalaOOP_Import {
    def main(args: Array[String]): Unit = {
        //1.scala中import作用为导类
        //2.import static: 导入类中的静态属性和方法
        //3.scala中import可以声明在任意位置
        import java.util._
        val date = new Date()
        //4.scala中默认导入的类：java.lang包下所有的类；scala包中的所有的类；Predef
        //5.scala中使用下划线代替java中的星号
        //6.使用import关键字将包中的类隐藏掉
        import java.sql.{Date=>_,_}
        //7.使用import关键字将指定类起别名(可以使用type来起别名)
//        import java.util.{Date=>UtilDate}
//        val date1 = new UtilDate().getTime
        type UtilDate = java.util.Date



    }
}
