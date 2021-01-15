package com.zg.scala

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-16-14:24
 */
object test2 {
  def main(args: Array[String]): Unit = {


    def fun88(name: String, password: String = "000000"): String = {
      //        println( name + "," + password )
      return name + password
    }

    println(fun88("zhangsan", "123123"))
    println(fun88("zhangsan"))


    def fun1(): String = {
      return "fyc"
    }

    //    1)	省略return关键字
    def fun2(): String = {
      "fyc"
    }

    //    2)	省略花括号
    def fun3(): String = "fyc"

    //    3)	省略返回值类型
    def fun4() = "fyc"


    //    4)	省略参数列表
    def fun5 = "fyc"


    //    如果函数体中有明确的return语句，那么返回值类型不能省略
    def fun6(): String = {
      return "fyc"
    }


    //    如果函数体返回值类型明确为Unit, 那么函数体中即使有return关键字也不起作用
    def fun7(): Unit = {
      "fyc"
    }

    //    如果函数体返回值类型明确为Unit, 那么函数体中即使有return关键字也不起作用
    def fun8(): Unit = {
      return "fyc"
    }


    //    6)	省略名称和关键字
    (i: Int, j: Int) => {
      return i + j;
    }

  }


}
