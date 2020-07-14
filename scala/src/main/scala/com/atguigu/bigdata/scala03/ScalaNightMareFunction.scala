package com.atguigu.bigdata.scala03

/**
 * @author lux_zhang
 * @create 2020-05-21 11:44
 */
object ScalaNightMareFunction {
    def main(args: Array[String]): Unit = {
        //函数至简原则：能省则省
        //当函数需要返回值时，可以将函数体中最后一行执行的代码作为返回结果
        def test(name: String): String = {
            s"name = $name"
        }

        val a1 = test("zhangsan")

        //如果编译器可以推断出函数的返回值类型，那么返回值类型可以省略
        def test1(name: String) = {
            s"name = $name"
        }

        //如果函数体只有一行代码，大括号可以省略
        def test2(name: String) = s"name = $name"

        //如果函数没有提供参数，那么调用的时候，小括号可以省略
        // 声明时，小括号也可以省略, 调用时，必须不能使用小括号
        def test3() = "zhangsan"
        //def test3 = "zhangsan"
        println(test3)

        /*
        函数如果明确使用Unit声明没有返回值，那么函数体中的return关键字不起作用的。
        函数体中如果明确使用return关键字，那么返回值类型不能省
        TODO 明确函数就是没有返回值，但是Unit又不想声明,那么可以同时省略等号
        将这种函数的声明方式称之为过程函数, 不省略花括号
         */
        def test4() {
            "zhangsan"
        }

        //匿名函数：当只关心代码逻辑，不关心函数名称时，函数名和def关键字可以省略
        //匿名函数规则： （参数列表） => { 代码逻辑 }
        //函数调用 ： 函数（变量）名称（参数列表）
        val a = () => "no name"
        println(a())
    }
}
