package com.atguigu.bigdata.scala06;

/**
 * @author lux_zhang
 * @create 2020-05-25 18:39
 */
public class Java_Access {
    public static void main(String[] args) throws Exception {
        /*
        Java的4种访问权限
        private   : 私有权限，  同类
        (default) : 包权限，    同类，同包
        protected : 受保护权限，同类，同包，子类
        public    : 公共权限，  任意的地方
         */
        //方法调用者到底是谁
        accessTest test = new accessTest();
        //clone()为java.lang.Object类中的protected方法，accessTest类继承Object，编译过程报错
        //test.clone();

        /*
        方法的实际调用者为main线程的com.atguigu.bigdata.scala06.Java_Access
        继承java.lang.Object并获得父类中的所有内容，子类包含父类
        Java_Access继承的Object不是accessTest继承的父类，所以无法通过accessTest的对象调用其父类中
            的protected的方法
         */
        //通过自己类的对象调用Object中的clone()编译不会报错
        Java_Access java = new Java_Access();
        java.clone();
        //通过父类的hashCode判断两个对象所在类的父类不是同一个
        System.out.println(test.superHashCode());
        System.out.println(java.superHashCode());

    }
    public int superHashCode(){
        return super.hashCode();
    }
}
class accessTest {
    public int superHashCode(){
        return super.hashCode();
    }
}
