package com.zg.scala.repratice

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-16-19:50
 */
object ScalaFunction2 {
  def main(args: Array[String]): Unit = {
    //    1)	省略return关键字

    def fun10(): String = {
      "1)\t省略return关键字"
    }

    //    2)	省略花括号

    def fun11(): String = "2)\t省略花括号"

    println(fun11())

    //    3)	省略返回值类型
    def fun12() = "3)\t省略返回值类型"

    println(fun12())

    //    4)	省略参数列表
    def fun13 = "4)\t省略参数列表"

    println(fun13)

    //    5)	省略等号 如果函数体返回值类型声明为Unit, 但是又想省略，那么此时就必须连同等号一起省略
    def fun15() {
      "5)\t省略等号"
    }

    //    6)	省略名称和关键字
    () => {
      println("6)\t省略名称和关键字")
    }


  }

}
