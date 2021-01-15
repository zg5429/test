package com.zg.scala.repratice

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-16-19:09
 */
object ScalaFunction {
  def main(args: Array[String]): Unit = {
    def test(s: String): Unit = {
      println(s)
    }


    //    1)	无参，无返回值

    def fun1() = {
      println("无参，无返回值")
    }

    //    2)	无参，有返回值
    def fun2(): String = {
      return "无参，有返回值"
    }

    //    3)	有参，无返回值

    def fun3(s: String) = {
      println("3)\t有参，无返回值")
    }

    //    4)	有参，有返回值

    def fun4(s: String): String = {
      return "s"
    }
    //    5)	多参，无返回值

    def fun5(name: String, age: Int) = {
      println(name + "" + age)
    }

//    6)	多参，有返回值
    def fun6(name:String,age:Int):String={
      return name+"  "+ age
    }
    println(fun6("lili",78))


  }


}
