package com.atguigu.bigdata.scala06;

/**
 * @author lux_zhang
 * @create 2020-05-25 20:38
 */
public class JavaMethodOverride {
    /*
    方法的重写一定要存在父子类。
    子类重写父类的方法。子类重写父类相同方法的逻辑
    方法名一致，参数列表保持一致，异常范围，访问权限
    调用时，无法确定到底执行哪一个方法，那么需要遵循 动态绑定机制:
            程序执行过程，如果调用了对象的“成员”“方法”时，会将方法和对象的实际内存进行绑定，然后调用。
    动态绑定机制和属性无关
     */
    public static void main(String[] args) {
//        Sun sun = new Sun();  40
        Father sun = new Sun(); //20
        System.out.println(sun.sum());

        System.out.println(sun.sumGetI());
    }
}
class Father {
    public int i = 10;
    public int sum(){
        return i + 10;
    }

    public int sumGetI(){
        return getI() + 10;
    }

    public int getI(){
        return i;
    }

}
class Sun extends Father {

    public int i = 20;
//    public int sum(){
//        return i + 20;
//    }

    public int getI(){
        return i;
    }
}