package com.atguigu.bigdata.scala05 {

    object test {
        println("******")
    }

    /**
     * @author lux_zhang
     * @create 2020-05-24 15:18
     */
    package scala {

        object ScalaOOP_Package {
            def main(args: Array[String]): Unit = {
                //scala中对package的语法进行功能的补充
                //1.package和源码的物理路径没有关系，scala会按照包的声明来编译指定路径的class
                //2.package关键字可以多次声明,当前类编译后会在最后的package指定的包中生成class文件
                //3.在package包名后面增加大括号，设定包的作用域范围以及层级关系

                //4.子包可以直接导入父包中的内容
                test
                //5.包也可以当成对象使用，但是为了和java中兼容，默认不能声明属性和函数，只能声明类。
                //      scala中通过包对象来完成上述需求
                println(package_name)
                packageExplain
            }
        }

    }

}

